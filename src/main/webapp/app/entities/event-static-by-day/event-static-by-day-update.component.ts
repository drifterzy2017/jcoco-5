import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';
import { EventStaticByDayService } from './event-static-by-day.service';

@Component({
    selector: 'jhi-event-static-by-day-update',
    templateUrl: './event-static-by-day-update.component.html'
})
export class EventStaticByDayUpdateComponent implements OnInit {
    eventStaticByDay: IEventStaticByDay;
    isSaving: boolean;

    constructor(protected eventStaticByDayService: EventStaticByDayService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eventStaticByDay }) => {
            this.eventStaticByDay = eventStaticByDay;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.eventStaticByDay.id !== undefined) {
            this.subscribeToSaveResponse(this.eventStaticByDayService.update(this.eventStaticByDay));
        } else {
            this.subscribeToSaveResponse(this.eventStaticByDayService.create(this.eventStaticByDay));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventStaticByDay>>) {
        result.subscribe((res: HttpResponse<IEventStaticByDay>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
