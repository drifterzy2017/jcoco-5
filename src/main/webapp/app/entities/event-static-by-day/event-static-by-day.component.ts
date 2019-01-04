import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';
import { AccountService } from 'app/core';
import { EventStaticByDayService } from './event-static-by-day.service';

@Component({
    selector: 'jhi-event-static-by-day',
    templateUrl: './event-static-by-day.component.html'
})
export class EventStaticByDayComponent implements OnInit, OnDestroy {
    eventStaticByDays: IEventStaticByDay[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected eventStaticByDayService: EventStaticByDayService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.eventStaticByDayService.query().subscribe(
            (res: HttpResponse<IEventStaticByDay[]>) => {
                this.eventStaticByDays = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEventStaticByDays();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEventStaticByDay) {
        return item.id;
    }

    registerChangeInEventStaticByDays() {
        this.eventSubscriber = this.eventManager.subscribe('eventStaticByDayListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
