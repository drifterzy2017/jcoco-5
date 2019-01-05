import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDeviceMask } from 'app/shared/model/device-mask.model';
import { AccountService } from 'app/core';
import { DeviceMaskService } from './device-mask.service';

@Component({
    selector: 'jhi-device-mask',
    templateUrl: './device-mask.component.html'
})
export class DeviceMaskComponent implements OnInit, OnDestroy {
    deviceMasks: IDeviceMask[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected deviceMaskService: DeviceMaskService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.deviceMaskService.query().subscribe(
            (res: HttpResponse<IDeviceMask[]>) => {
                this.deviceMasks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDeviceMasks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDeviceMask) {
        return item.id;
    }

    registerChangeInDeviceMasks() {
        this.eventSubscriber = this.eventManager.subscribe('deviceMaskListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
