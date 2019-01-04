import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICoreSource } from 'app/shared/model/core-source.model';
import { CoreSourceService } from './core-source.service';

@Component({
    selector: 'jhi-core-source-update',
    templateUrl: './core-source-update.component.html'
})
export class CoreSourceUpdateComponent implements OnInit {
    coreSource: ICoreSource;
    isSaving: boolean;

    constructor(protected coreSourceService: CoreSourceService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coreSource }) => {
            this.coreSource = coreSource;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.coreSource.id !== undefined) {
            this.subscribeToSaveResponse(this.coreSourceService.update(this.coreSource));
        } else {
            this.subscribeToSaveResponse(this.coreSourceService.create(this.coreSource));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICoreSource>>) {
        result.subscribe((res: HttpResponse<ICoreSource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
