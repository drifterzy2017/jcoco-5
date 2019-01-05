import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDeviceMask } from 'app/shared/model/device-mask.model';
import { DeviceMaskService } from './device-mask.service';

@Component({
    selector: 'jhi-device-mask-update',
    templateUrl: './device-mask-update.component.html'
})
export class DeviceMaskUpdateComponent implements OnInit {
    deviceMask: IDeviceMask;
    isSaving: boolean;
    operationTime: string;

    constructor(protected deviceMaskService: DeviceMaskService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deviceMask }) => {
            this.deviceMask = deviceMask;
            this.operationTime = this.deviceMask.operationTime != null ? this.deviceMask.operationTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.deviceMask.operationTime = this.operationTime != null ? moment(this.operationTime, DATE_TIME_FORMAT) : null;
        if (this.deviceMask.id !== undefined) {
            this.subscribeToSaveResponse(this.deviceMaskService.update(this.deviceMask));
        } else {
            this.subscribeToSaveResponse(this.deviceMaskService.create(this.deviceMask));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeviceMask>>) {
        result.subscribe((res: HttpResponse<IDeviceMask>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
