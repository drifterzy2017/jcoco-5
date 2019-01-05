import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICov } from 'app/shared/model/cov.model';

type EntityResponseType = HttpResponse<ICov>;
type EntityArrayResponseType = HttpResponse<ICov[]>;

@Injectable({ providedIn: 'root' })
export class CovService {
    public resourceUrl = SERVER_API_URL + 'api/covs';

    constructor(protected http: HttpClient) {}

    create(cov: ICov): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cov);
        return this.http
            .post<ICov>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(cov: ICov): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cov);
        return this.http
            .put<ICov>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICov>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICov[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(cov: ICov): ICov {
        const copy: ICov = Object.assign({}, cov, {
            birthTime: cov.birthTime != null && cov.birthTime.isValid() ? cov.birthTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.birthTime = res.body.birthTime != null ? moment(res.body.birthTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((cov: ICov) => {
                cov.birthTime = cov.birthTime != null ? moment(cov.birthTime) : null;
            });
        }
        return res;
    }
}
