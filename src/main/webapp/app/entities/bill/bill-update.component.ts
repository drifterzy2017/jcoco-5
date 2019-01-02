import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBill } from 'app/shared/model/bill.model';
import { BillService } from './bill.service';

@Component({
    selector: 'jhi-bill-update',
    templateUrl: './bill-update.component.html'
})
export class BillUpdateComponent implements OnInit {
    bill: IBill;
    isSaving: boolean;
    buyTime: string;

    constructor(protected billService: BillService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bill }) => {
            this.bill = bill;
            this.buyTime = this.bill.buyTime != null ? this.bill.buyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.bill.buyTime = this.buyTime != null ? moment(this.buyTime, DATE_TIME_FORMAT) : null;
        if (this.bill.id !== undefined) {
            this.subscribeToSaveResponse(this.billService.update(this.bill));
        } else {
            this.subscribeToSaveResponse(this.billService.create(this.bill));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBill>>) {
        result.subscribe((res: HttpResponse<IBill>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
