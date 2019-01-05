import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILivePoint } from 'app/shared/model/live-point.model';

type EntityResponseType = HttpResponse<ILivePoint>;
type EntityArrayResponseType = HttpResponse<ILivePoint[]>;

@Injectable({ providedIn: 'root' })
export class LivePointService {
    public resourceUrl = SERVER_API_URL + 'api/live-points';

    constructor(protected http: HttpClient) {}

    create(livePoint: ILivePoint): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(livePoint);
        return this.http
            .post<ILivePoint>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(livePoint: ILivePoint): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(livePoint);
        return this.http
            .put<ILivePoint>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILivePoint>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILivePoint[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(livePoint: ILivePoint): ILivePoint {
        const copy: ILivePoint = Object.assign({}, livePoint, {
            birthTime: livePoint.birthTime != null && livePoint.birthTime.isValid() ? livePoint.birthTime.toJSON() : null
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
            res.body.forEach((livePoint: ILivePoint) => {
                livePoint.birthTime = livePoint.birthTime != null ? moment(livePoint.birthTime) : null;
            });
        }
        return res;
    }
}
