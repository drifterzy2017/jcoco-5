import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDeviceState } from 'app/shared/model/device-state.model';
import { DeviceStateService } from './device-state.service';

@Component({
    selector: 'jhi-device-state-update',
    templateUrl: './device-state-update.component.html'
})
export class DeviceStateUpdateComponent implements OnInit {
    deviceState: IDeviceState;
    isSaving: boolean;

    constructor(protected deviceStateService: DeviceStateService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deviceState }) => {
            this.deviceState = deviceState;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.deviceState.id !== undefined) {
            this.subscribeToSaveResponse(this.deviceStateService.update(this.deviceState));
        } else {
            this.subscribeToSaveResponse(this.deviceStateService.create(this.deviceState));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeviceState>>) {
        result.subscribe((res: HttpResponse<IDeviceState>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
