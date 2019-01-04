import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';

type EntityResponseType = HttpResponse<IEventStaticByDay>;
type EntityArrayResponseType = HttpResponse<IEventStaticByDay[]>;

@Injectable({ providedIn: 'root' })
export class EventStaticByDayService {
    public resourceUrl = SERVER_API_URL + 'api/event-static-by-days';

    constructor(protected http: HttpClient) {}

    create(eventStaticByDay: IEventStaticByDay): Observable<EntityResponseType> {
        return this.http.post<IEventStaticByDay>(this.resourceUrl, eventStaticByDay, { observe: 'response' });
    }

    update(eventStaticByDay: IEventStaticByDay): Observable<EntityResponseType> {
        return this.http.put<IEventStaticByDay>(this.resourceUrl, eventStaticByDay, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEventStaticByDay>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEventStaticByDay[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
