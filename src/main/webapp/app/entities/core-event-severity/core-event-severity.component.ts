import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';
import { AccountService } from 'app/core';
import { CoreEventSeverityService } from './core-event-severity.service';

@Component({
    selector: 'jhi-core-event-severity',
    templateUrl: './core-event-severity.component.html'
})
export class CoreEventSeverityComponent implements OnInit, OnDestroy {
    coreEventSeverities: ICoreEventSeverity[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected coreEventSeverityService: CoreEventSeverityService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.coreEventSeverityService.query().subscribe(
            (res: HttpResponse<ICoreEventSeverity[]>) => {
                this.coreEventSeverities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCoreEventSeverities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICoreEventSeverity) {
        return item.id;
    }

    registerChangeInCoreEventSeverities() {
        this.eventSubscriber = this.eventManager.subscribe('coreEventSeverityListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
