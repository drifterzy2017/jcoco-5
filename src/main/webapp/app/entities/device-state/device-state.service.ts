import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeviceState } from 'app/shared/model/device-state.model';

type EntityResponseType = HttpResponse<IDeviceState>;
type EntityArrayResponseType = HttpResponse<IDeviceState[]>;

@Injectable({ providedIn: 'root' })
export class DeviceStateService {
    public resourceUrl = SERVER_API_URL + 'api/device-states';

    constructor(protected http: HttpClient) {}

    create(deviceState: IDeviceState): Observable<EntityResponseType> {
        return this.http.post<IDeviceState>(this.resourceUrl, deviceState, { observe: 'response' });
    }

    update(deviceState: IDeviceState): Observable<EntityResponseType> {
        return this.http.put<IDeviceState>(this.resourceUrl, deviceState, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDeviceState>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDeviceState[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
