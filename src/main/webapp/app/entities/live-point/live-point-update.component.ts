import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILivePoint } from 'app/shared/model/live-point.model';
import { LivePointService } from './live-point.service';

@Component({
    selector: 'jhi-live-point-update',
    templateUrl: './live-point-update.component.html'
})
export class LivePointUpdateComponent implements OnInit {
    livePoint: ILivePoint;
    isSaving: boolean;
    birthTime: string;

    constructor(protected livePointService: LivePointService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ livePoint }) => {
            this.livePoint = livePoint;
            this.birthTime = this.livePoint.birthTime != null ? this.livePoint.birthTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.livePoint.birthTime = this.birthTime != null ? moment(this.birthTime, DATE_TIME_FORMAT) : null;
        if (this.livePoint.id !== undefined) {
            this.subscribeToSaveResponse(this.livePointService.update(this.livePoint));
        } else {
            this.subscribeToSaveResponse(this.livePointService.create(this.livePoint));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILivePoint>>) {
        result.subscribe((res: HttpResponse<ILivePoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
