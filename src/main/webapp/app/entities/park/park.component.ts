import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPark } from 'app/shared/model/park.model';
import { AccountService } from 'app/core';
import { ParkService } from './park.service';

@Component({
    selector: 'jhi-park',
    templateUrl: './park.component.html'
})
export class ParkComponent implements OnInit, OnDestroy {
    parks: IPark[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected parkService: ParkService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.parkService.query().subscribe(
            (res: HttpResponse<IPark[]>) => {
                this.parks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInParks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPark) {
        return item.id;
    }

    registerChangeInParks() {
        this.eventSubscriber = this.eventManager.subscribe('parkListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
