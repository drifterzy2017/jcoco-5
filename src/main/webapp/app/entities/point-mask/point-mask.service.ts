import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPointMask } from 'app/shared/model/point-mask.model';

type EntityResponseType = HttpResponse<IPointMask>;
type EntityArrayResponseType = HttpResponse<IPointMask[]>;

@Injectable({ providedIn: 'root' })
export class PointMaskService {
    public resourceUrl = SERVER_API_URL + 'api/point-masks';

    constructor(protected http: HttpClient) {}

    create(pointMask: IPointMask): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pointMask);
        return this.http
            .post<IPointMask>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pointMask: IPointMask): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pointMask);
        return this.http
            .put<IPointMask>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPointMask>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPointMask[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(pointMask: IPointMask): IPointMask {
        const copy: IPointMask = Object.assign({}, pointMask, {
            operationTime: pointMask.operationTime != null && pointMask.operationTime.isValid() ? pointMask.operationTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.operationTime = res.body.operationTime != null ? moment(res.body.operationTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((pointMask: IPointMask) => {
                pointMask.operationTime = pointMask.operationTime != null ? moment(pointMask.operationTime) : null;
            });
        }
        return res;
    }
}
