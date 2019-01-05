import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICov } from 'app/shared/model/cov.model';
import { CovService } from './cov.service';

@Component({
    selector: 'jhi-cov-update',
    templateUrl: './cov-update.component.html'
})
export class CovUpdateComponent implements OnInit {
    cov: ICov;
    isSaving: boolean;
    birthTime: string;

    constructor(protected covService: CovService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cov }) => {
            this.cov = cov;
            this.birthTime = this.cov.birthTime != null ? this.cov.birthTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.cov.birthTime = this.birthTime != null ? moment(this.birthTime, DATE_TIME_FORMAT) : null;
        if (this.cov.id !== undefined) {
            this.subscribeToSaveResponse(this.covService.update(this.cov));
        } else {
            this.subscribeToSaveResponse(this.covService.create(this.cov));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICov>>) {
        result.subscribe((res: HttpResponse<ICov>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
