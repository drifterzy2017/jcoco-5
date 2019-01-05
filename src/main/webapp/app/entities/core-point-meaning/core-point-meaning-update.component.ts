import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';
import { CorePointMeaningService } from './core-point-meaning.service';

@Component({
    selector: 'jhi-core-point-meaning-update',
    templateUrl: './core-point-meaning-update.component.html'
})
export class CorePointMeaningUpdateComponent implements OnInit {
    corePointMeaning: ICorePointMeaning;
    isSaving: boolean;

    constructor(protected corePointMeaningService: CorePointMeaningService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ corePointMeaning }) => {
            this.corePointMeaning = corePointMeaning;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.corePointMeaning.id !== undefined) {
            this.subscribeToSaveResponse(this.corePointMeaningService.update(this.corePointMeaning));
        } else {
            this.subscribeToSaveResponse(this.corePointMeaningService.create(this.corePointMeaning));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICorePointMeaning>>) {
        result.subscribe((res: HttpResponse<ICorePointMeaning>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
