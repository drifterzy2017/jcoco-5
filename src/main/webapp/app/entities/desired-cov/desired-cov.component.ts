import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDesiredCov } from 'app/shared/model/desired-cov.model';
import { AccountService } from 'app/core';
import { DesiredCovService } from './desired-cov.service';

@Component({
    selector: 'jhi-desired-cov',
    templateUrl: './desired-cov.component.html'
})
export class DesiredCovComponent implements OnInit, OnDestroy {
    desiredCovs: IDesiredCov[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected desiredCovService: DesiredCovService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.desiredCovService.query().subscribe(
            (res: HttpResponse<IDesiredCov[]>) => {
                this.desiredCovs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDesiredCovs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDesiredCov) {
        return item.id;
    }

    registerChangeInDesiredCovs() {
        this.eventSubscriber = this.eventManager.subscribe('desiredCovListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
