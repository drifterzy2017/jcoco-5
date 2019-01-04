import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeviceState } from 'app/shared/model/device-state.model';

@Component({
    selector: 'jhi-device-state-detail',
    templateUrl: './device-state-detail.component.html'
})
export class DeviceStateDetailComponent implements OnInit {
    deviceState: IDeviceState;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceState }) => {
            this.deviceState = deviceState;
        });
    }

    previousState() {
        window.history.back();
    }
}
