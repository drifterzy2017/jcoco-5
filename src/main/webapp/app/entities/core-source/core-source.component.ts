import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICoreSource } from 'app/shared/model/core-source.model';
import { AccountService } from 'app/core';
import { CoreSourceService } from './core-source.service';

@Component({
    selector: 'jhi-core-source',
    templateUrl: './core-source.component.html'
})
export class CoreSourceComponent implements OnInit, OnDestroy {
    coreSources: ICoreSource[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected coreSourceService: CoreSourceService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.coreSourceService.query().subscribe(
            (res: HttpResponse<ICoreSource[]>) => {
                this.coreSources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCoreSources();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICoreSource) {
        return item.id;
    }

    registerChangeInCoreSources() {
        this.eventSubscriber = this.eventManager.subscribe('coreSourceListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
