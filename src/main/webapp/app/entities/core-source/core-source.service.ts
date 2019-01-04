import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICoreSource } from 'app/shared/model/core-source.model';

type EntityResponseType = HttpResponse<ICoreSource>;
type EntityArrayResponseType = HttpResponse<ICoreSource[]>;

@Injectable({ providedIn: 'root' })
export class CoreSourceService {
    public resourceUrl = SERVER_API_URL + 'api/core-sources';

    constructor(protected http: HttpClient) {}

    create(coreSource: ICoreSource): Observable<EntityResponseType> {
        return this.http.post<ICoreSource>(this.resourceUrl, coreSource, { observe: 'response' });
    }

    update(coreSource: ICoreSource): Observable<EntityResponseType> {
        return this.http.put<ICoreSource>(this.resourceUrl, coreSource, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICoreSource>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICoreSource[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
