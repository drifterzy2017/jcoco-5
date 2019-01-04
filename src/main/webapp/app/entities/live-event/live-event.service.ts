import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILiveEvent } from 'app/shared/model/live-event.model';

type EntityResponseType = HttpResponse<ILiveEvent>;
type EntityArrayResponseType = HttpResponse<ILiveEvent[]>;

@Injectable({ providedIn: 'root' })
export class LiveEventService {
    public resourceUrl = SERVER_API_URL + 'api/live-events';

    constructor(protected http: HttpClient) {}

    create(liveEvent: ILiveEvent): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(liveEvent);
        return this.http
            .post<ILiveEvent>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(liveEvent: ILiveEvent): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(liveEvent);
        return this.http
            .put<ILiveEvent>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILiveEvent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILiveEvent[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(liveEvent: ILiveEvent): ILiveEvent {
        const copy: ILiveEvent = Object.assign({}, liveEvent, {
            birthTime: liveEvent.birthTime != null && liveEvent.birthTime.isValid() ? liveEvent.birthTime.toJSON() : null,
            clearTime: liveEvent.clearTime != null && liveEvent.clearTime.isValid() ? liveEvent.clearTime.toJSON() : null,
            confirmTime: liveEvent.confirmTime != null && liveEvent.confirmTime.isValid() ? liveEvent.confirmTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.birthTime = res.body.birthTime != null ? moment(res.body.birthTime) : null;
            res.body.clearTime = res.body.clearTime != null ? moment(res.body.clearTime) : null;
            res.body.confirmTime = res.body.confirmTime != null ? moment(res.body.confirmTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((liveEvent: ILiveEvent) => {
                liveEvent.birthTime = liveEvent.birthTime != null ? moment(liveEvent.birthTime) : null;
                liveEvent.clearTime = liveEvent.clearTime != null ? moment(liveEvent.clearTime) : null;
                liveEvent.confirmTime = liveEvent.confirmTime != null ? moment(liveEvent.confirmTime) : null;
            });
        }
        return res;
    }
}
