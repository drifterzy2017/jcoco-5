import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDevice } from 'app/shared/model/device.model';
import { AccountService } from 'app/core';
import { DeviceService } from './device.service';

@Component({
    selector: 'jhi-device',
    templateUrl: './device.component.html'
})
export class DeviceComponent implements OnInit, OnDestroy {
    devices: IDevice[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected deviceService: DeviceService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.deviceService.query().subscribe(
            (res: HttpResponse<IDevice[]>) => {
                this.devices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDevices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDevice) {
        return item.id;
    }

    registerChangeInDevices() {
        this.eventSubscriber = this.eventManager.subscribe('deviceListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
