import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPointMask } from 'app/shared/model/point-mask.model';
import { AccountService } from 'app/core';
import { PointMaskService } from './point-mask.service';

@Component({
    selector: 'jhi-point-mask',
    templateUrl: './point-mask.component.html'
})
export class PointMaskComponent implements OnInit, OnDestroy {
    pointMasks: IPointMask[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected pointMaskService: PointMaskService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.pointMaskService.query().subscribe(
            (res: HttpResponse<IPointMask[]>) => {
                this.pointMasks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPointMasks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPointMask) {
        return item.id;
    }

    registerChangeInPointMasks() {
        this.eventSubscriber = this.eventManager.subscribe('pointMaskListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
