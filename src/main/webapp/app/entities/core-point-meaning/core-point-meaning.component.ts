import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';
import { AccountService } from 'app/core';
import { CorePointMeaningService } from './core-point-meaning.service';

@Component({
    selector: 'jhi-core-point-meaning',
    templateUrl: './core-point-meaning.component.html'
})
export class CorePointMeaningComponent implements OnInit, OnDestroy {
    corePointMeanings: ICorePointMeaning[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected corePointMeaningService: CorePointMeaningService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.corePointMeaningService.query().subscribe(
            (res: HttpResponse<ICorePointMeaning[]>) => {
                this.corePointMeanings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCorePointMeanings();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICorePointMeaning) {
        return item.id;
    }

    registerChangeInCorePointMeanings() {
        this.eventSubscriber = this.eventManager.subscribe('corePointMeaningListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
