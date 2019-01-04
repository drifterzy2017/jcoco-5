import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';
import { CoreEventSeverityService } from './core-event-severity.service';

@Component({
    selector: 'jhi-core-event-severity-update',
    templateUrl: './core-event-severity-update.component.html'
})
export class CoreEventSeverityUpdateComponent implements OnInit {
    coreEventSeverity: ICoreEventSeverity;
    isSaving: boolean;

    constructor(protected coreEventSeverityService: CoreEventSeverityService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coreEventSeverity }) => {
            this.coreEventSeverity = coreEventSeverity;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.coreEventSeverity.id !== undefined) {
            this.subscribeToSaveResponse(this.coreEventSeverityService.update(this.coreEventSeverity));
        } else {
            this.subscribeToSaveResponse(this.coreEventSeverityService.create(this.coreEventSeverity));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICoreEventSeverity>>) {
        result.subscribe((res: HttpResponse<ICoreEventSeverity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
