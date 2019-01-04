import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPark } from 'app/shared/model/park.model';
import { ParkService } from './park.service';

@Component({
    selector: 'jhi-park-update',
    templateUrl: './park-update.component.html'
})
export class ParkUpdateComponent implements OnInit {
    park: IPark;
    isSaving: boolean;

    constructor(protected parkService: ParkService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ park }) => {
            this.park = park;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.park.id !== undefined) {
            this.subscribeToSaveResponse(this.parkService.update(this.park));
        } else {
            this.subscribeToSaveResponse(this.parkService.create(this.park));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPark>>) {
        result.subscribe((res: HttpResponse<IPark>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
