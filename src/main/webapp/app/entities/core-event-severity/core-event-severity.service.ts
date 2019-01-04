import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';

type EntityResponseType = HttpResponse<ICoreEventSeverity>;
type EntityArrayResponseType = HttpResponse<ICoreEventSeverity[]>;

@Injectable({ providedIn: 'root' })
export class CoreEventSeverityService {
    public resourceUrl = SERVER_API_URL + 'api/core-event-severities';

    constructor(protected http: HttpClient) {}

    create(coreEventSeverity: ICoreEventSeverity): Observable<EntityResponseType> {
        return this.http.post<ICoreEventSeverity>(this.resourceUrl, coreEventSeverity, { observe: 'response' });
    }

    update(coreEventSeverity: ICoreEventSeverity): Observable<EntityResponseType> {
        return this.http.put<ICoreEventSeverity>(this.resourceUrl, coreEventSeverity, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICoreEventSeverity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICoreEventSeverity[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
