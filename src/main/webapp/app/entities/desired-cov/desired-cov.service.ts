import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDesiredCov } from 'app/shared/model/desired-cov.model';

type EntityResponseType = HttpResponse<IDesiredCov>;
type EntityArrayResponseType = HttpResponse<IDesiredCov[]>;

@Injectable({ providedIn: 'root' })
export class DesiredCovService {
    public resourceUrl = SERVER_API_URL + 'api/desired-covs';

    constructor(protected http: HttpClient) {}

    create(desiredCov: IDesiredCov): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(desiredCov);
        return this.http
            .post<IDesiredCov>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(desiredCov: IDesiredCov): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(desiredCov);
        return this.http
            .put<IDesiredCov>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDesiredCov>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDesiredCov[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(desiredCov: IDesiredCov): IDesiredCov {
        const copy: IDesiredCov = Object.assign({}, desiredCov, {
            birthTime: desiredCov.birthTime != null && desiredCov.birthTime.isValid() ? desiredCov.birthTime.toJSON() : null
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
            res.body.forEach((desiredCov: IDesiredCov) => {
                desiredCov.birthTime = desiredCov.birthTime != null ? moment(desiredCov.birthTime) : null;
            });
        }
        return res;
    }
}
