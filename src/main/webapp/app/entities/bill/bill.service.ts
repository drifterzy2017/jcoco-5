import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBill } from 'app/shared/model/bill.model';

type EntityResponseType = HttpResponse<IBill>;
type EntityArrayResponseType = HttpResponse<IBill[]>;

@Injectable({ providedIn: 'root' })
export class BillService {
    public resourceUrl = SERVER_API_URL + 'api/bills';

    constructor(protected http: HttpClient) {}

    create(bill: IBill): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bill);
        return this.http
            .post<IBill>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(bill: IBill): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(bill);
        return this.http
            .put<IBill>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBill>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBill[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(bill: IBill): IBill {
        const copy: IBill = Object.assign({}, bill, {
            buyTime: bill.buyTime != null && bill.buyTime.isValid() ? bill.buyTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.buyTime = res.body.buyTime != null ? moment(res.body.buyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((bill: IBill) => {
                bill.buyTime = bill.buyTime != null ? moment(bill.buyTime) : null;
            });
        }
        return res;
    }
}
