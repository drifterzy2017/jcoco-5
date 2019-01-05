import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICorePoint } from 'app/shared/model/core-point.model';

type EntityResponseType = HttpResponse<ICorePoint>;
type EntityArrayResponseType = HttpResponse<ICorePoint[]>;

@Injectable({ providedIn: 'root' })
export class CorePointService {
    public resourceUrl = SERVER_API_URL + 'api/core-points';

    constructor(protected http: HttpClient) {}

    create(corePoint: ICorePoint): Observable<EntityResponseType> {
        return this.http.post<ICorePoint>(this.resourceUrl, corePoint, { observe: 'response' });
    }

    update(corePoint: ICorePoint): Observable<EntityResponseType> {
        return this.http.put<ICorePoint>(this.resourceUrl, corePoint, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICorePoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICorePoint[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
