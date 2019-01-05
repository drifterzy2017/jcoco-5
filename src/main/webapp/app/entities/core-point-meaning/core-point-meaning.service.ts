import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';

type EntityResponseType = HttpResponse<ICorePointMeaning>;
type EntityArrayResponseType = HttpResponse<ICorePointMeaning[]>;

@Injectable({ providedIn: 'root' })
export class CorePointMeaningService {
    public resourceUrl = SERVER_API_URL + 'api/core-point-meanings';

    constructor(protected http: HttpClient) {}

    create(corePointMeaning: ICorePointMeaning): Observable<EntityResponseType> {
        return this.http.post<ICorePointMeaning>(this.resourceUrl, corePointMeaning, { observe: 'response' });
    }

    update(corePointMeaning: ICorePointMeaning): Observable<EntityResponseType> {
        return this.http.put<ICorePointMeaning>(this.resourceUrl, corePointMeaning, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICorePointMeaning>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICorePointMeaning[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
