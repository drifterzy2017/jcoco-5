import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPointMask } from 'app/shared/model/point-mask.model';
import { PointMaskService } from './point-mask.service';

@Component({
    selector: 'jhi-point-mask-update',
    templateUrl: './point-mask-update.component.html'
})
export class PointMaskUpdateComponent implements OnInit {
    pointMask: IPointMask;
    isSaving: boolean;
    operationTime: string;

    constructor(protected pointMaskService: PointMaskService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pointMask }) => {
            this.pointMask = pointMask;
            this.operationTime = this.pointMask.operationTime != null ? this.pointMask.operationTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pointMask.operationTime = this.operationTime != null ? moment(this.operationTime, DATE_TIME_FORMAT) : null;
        if (this.pointMask.id !== undefined) {
            this.subscribeToSaveResponse(this.pointMaskService.update(this.pointMask));
        } else {
            this.subscribeToSaveResponse(this.pointMaskService.create(this.pointMask));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPointMask>>) {
        result.subscribe((res: HttpResponse<IPointMask>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
