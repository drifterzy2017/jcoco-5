import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILiveEvent } from 'app/shared/model/live-event.model';
import { LiveEventService } from './live-event.service';

@Component({
    selector: 'jhi-live-event-update',
    templateUrl: './live-event-update.component.html'
})
export class LiveEventUpdateComponent implements OnInit {
    liveEvent: ILiveEvent;
    isSaving: boolean;
    birthTime: string;
    clearTime: string;
    confirmTime: string;

    constructor(protected liveEventService: LiveEventService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ liveEvent }) => {
            this.liveEvent = liveEvent;
            this.birthTime = this.liveEvent.birthTime != null ? this.liveEvent.birthTime.format(DATE_TIME_FORMAT) : null;
            this.clearTime = this.liveEvent.clearTime != null ? this.liveEvent.clearTime.format(DATE_TIME_FORMAT) : null;
            this.confirmTime = this.liveEvent.confirmTime != null ? this.liveEvent.confirmTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.liveEvent.birthTime = this.birthTime != null ? moment(this.birthTime, DATE_TIME_FORMAT) : null;
        this.liveEvent.clearTime = this.clearTime != null ? moment(this.clearTime, DATE_TIME_FORMAT) : null;
        this.liveEvent.confirmTime = this.confirmTime != null ? moment(this.confirmTime, DATE_TIME_FORMAT) : null;
        if (this.liveEvent.id !== undefined) {
            this.subscribeToSaveResponse(this.liveEventService.update(this.liveEvent));
        } else {
            this.subscribeToSaveResponse(this.liveEventService.create(this.liveEvent));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILiveEvent>>) {
        result.subscribe((res: HttpResponse<ILiveEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
