import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDeviceState } from 'app/shared/model/device-state.model';
import { AccountService } from 'app/core';
import { DeviceStateService } from './device-state.service';

@Component({
    selector: 'jhi-device-state',
    templateUrl: './device-state.component.html'
})
export class DeviceStateComponent implements OnInit, OnDestroy {
    deviceStates: IDeviceState[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected deviceStateService: DeviceStateService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.deviceStateService.query().subscribe(
            (res: HttpResponse<IDeviceState[]>) => {
                this.deviceStates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDeviceStates();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDeviceState) {
        return item.id;
    }

    registerChangeInDeviceStates() {
        this.eventSubscriber = this.eventManager.subscribe('deviceStateListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
