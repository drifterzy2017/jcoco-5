import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICorePoint } from 'app/shared/model/core-point.model';
import { CorePointService } from './core-point.service';

@Component({
    selector: 'jhi-core-point-update',
    templateUrl: './core-point-update.component.html'
})
export class CorePointUpdateComponent implements OnInit {
    corePoint: ICorePoint;
    isSaving: boolean;

    constructor(protected corePointService: CorePointService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ corePoint }) => {
            this.corePoint = corePoint;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.corePoint.id !== undefined) {
            this.subscribeToSaveResponse(this.corePointService.update(this.corePoint));
        } else {
            this.subscribeToSaveResponse(this.corePointService.create(this.corePoint));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICorePoint>>) {
        result.subscribe((res: HttpResponse<ICorePoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
