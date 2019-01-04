import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFloor } from 'app/shared/model/floor.model';

type EntityResponseType = HttpResponse<IFloor>;
type EntityArrayResponseType = HttpResponse<IFloor[]>;

@Injectable({ providedIn: 'root' })
export class FloorService {
    public resourceUrl = SERVER_API_URL + 'api/floors';

    constructor(protected http: HttpClient) {}

    create(floor: IFloor): Observable<EntityResponseType> {
        return this.http.post<IFloor>(this.resourceUrl, floor, { observe: 'response' });
    }

    update(floor: IFloor): Observable<EntityResponseType> {
        return this.http.put<IFloor>(this.resourceUrl, floor, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFloor>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFloor[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
