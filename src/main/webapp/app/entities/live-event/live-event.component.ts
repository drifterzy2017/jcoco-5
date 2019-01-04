import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILiveEvent } from 'app/shared/model/live-event.model';
import { AccountService } from 'app/core';
import { LiveEventService } from './live-event.service';

@Component({
    selector: 'jhi-live-event',
    templateUrl: './live-event.component.html'
})
export class LiveEventComponent implements OnInit, OnDestroy {
    liveEvents: ILiveEvent[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected liveEventService: LiveEventService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.liveEventService.query().subscribe(
            (res: HttpResponse<ILiveEvent[]>) => {
                this.liveEvents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLiveEvents();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILiveEvent) {
        return item.id;
    }

    registerChangeInLiveEvents() {
        this.eventSubscriber = this.eventManager.subscribe('liveEventListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
