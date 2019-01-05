import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILivePoint } from 'app/shared/model/live-point.model';
import { AccountService } from 'app/core';
import { LivePointService } from './live-point.service';

@Component({
    selector: 'jhi-live-point',
    templateUrl: './live-point.component.html'
})
export class LivePointComponent implements OnInit, OnDestroy {
    livePoints: ILivePoint[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected livePointService: LivePointService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.livePointService.query().subscribe(
            (res: HttpResponse<ILivePoint[]>) => {
                this.livePoints = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLivePoints();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILivePoint) {
        return item.id;
    }

    registerChangeInLivePoints() {
        this.eventSubscriber = this.eventManager.subscribe('livePointListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
