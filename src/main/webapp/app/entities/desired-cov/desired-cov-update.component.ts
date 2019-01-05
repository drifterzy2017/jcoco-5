import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDesiredCov } from 'app/shared/model/desired-cov.model';
import { DesiredCovService } from './desired-cov.service';

@Component({
    selector: 'jhi-desired-cov-update',
    templateUrl: './desired-cov-update.component.html'
})
export class DesiredCovUpdateComponent implements OnInit {
    desiredCov: IDesiredCov;
    isSaving: boolean;
    birthTime: string;

    constructor(protected desiredCovService: DesiredCovService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ desiredCov }) => {
            this.desiredCov = desiredCov;
            this.birthTime = this.desiredCov.birthTime != null ? this.desiredCov.birthTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.desiredCov.birthTime = this.birthTime != null ? moment(this.birthTime, DATE_TIME_FORMAT) : null;
        if (this.desiredCov.id !== undefined) {
            this.subscribeToSaveResponse(this.desiredCovService.update(this.desiredCov));
        } else {
            this.subscribeToSaveResponse(this.desiredCovService.create(this.desiredCov));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDesiredCov>>) {
        result.subscribe((res: HttpResponse<IDesiredCov>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
