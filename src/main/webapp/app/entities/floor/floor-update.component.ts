import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IFloor } from 'app/shared/model/floor.model';
import { FloorService } from './floor.service';

@Component({
    selector: 'jhi-floor-update',
    templateUrl: './floor-update.component.html'
})
export class FloorUpdateComponent implements OnInit {
    floor: IFloor;
    isSaving: boolean;

    constructor(protected floorService: FloorService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ floor }) => {
            this.floor = floor;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.floor.id !== undefined) {
            this.subscribeToSaveResponse(this.floorService.update(this.floor));
        } else {
            this.subscribeToSaveResponse(this.floorService.create(this.floor));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFloor>>) {
        result.subscribe((res: HttpResponse<IFloor>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
