import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDeviceMask } from 'app/shared/model/device-mask.model';

type EntityResponseType = HttpResponse<IDeviceMask>;
type EntityArrayResponseType = HttpResponse<IDeviceMask[]>;

@Injectable({ providedIn: 'root' })
export class DeviceMaskService {
    public resourceUrl = SERVER_API_URL + 'api/device-masks';

    constructor(protected http: HttpClient) {}

    create(deviceMask: IDeviceMask): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deviceMask);
        return this.http
            .post<IDeviceMask>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(deviceMask: IDeviceMask): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(deviceMask);
        return this.http
            .put<IDeviceMask>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDeviceMask>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDeviceMask[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(deviceMask: IDeviceMask): IDeviceMask {
        const copy: IDeviceMask = Object.assign({}, deviceMask, {
            operationTime: deviceMask.operationTime != null && deviceMask.operationTime.isValid() ? deviceMask.operationTime.toJSON() : null
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
            res.body.forEach((deviceMask: IDeviceMask) => {
                deviceMask.operationTime = deviceMask.operationTime != null ? moment(deviceMask.operationTime) : null;
            });
        }
        return res;
    }
}
