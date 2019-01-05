import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeviceMask } from 'app/shared/model/device-mask.model';

@Component({
    selector: 'jhi-device-mask-detail',
    templateUrl: './device-mask-detail.component.html'
})
export class DeviceMaskDetailComponent implements OnInit {
    deviceMask: IDeviceMask;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceMask }) => {
            this.deviceMask = deviceMask;
        });
    }

    previousState() {
        window.history.back();
    }
}
