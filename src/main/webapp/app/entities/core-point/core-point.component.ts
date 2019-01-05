import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICorePoint } from 'app/shared/model/core-point.model';
import { AccountService } from 'app/core';
import { CorePointService } from './core-point.service';

@Component({
    selector: 'jhi-core-point',
    templateUrl: './core-point.component.html'
})
export class CorePointComponent implements OnInit, OnDestroy {
    corePoints: ICorePoint[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected corePointService: CorePointService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.corePointService.query().subscribe(
            (res: HttpResponse<ICorePoint[]>) => {
                this.corePoints = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCorePoints();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICorePoint) {
        return item.id;
    }

    registerChangeInCorePoints() {
        this.eventSubscriber = this.eventManager.subscribe('corePointListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
