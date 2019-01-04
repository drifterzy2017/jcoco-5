import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IFloor } from 'app/shared/model/floor.model';
import { AccountService } from 'app/core';
import { FloorService } from './floor.service';

@Component({
    selector: 'jhi-floor',
    templateUrl: './floor.component.html'
})
export class FloorComponent implements OnInit, OnDestroy {
    floors: IFloor[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected floorService: FloorService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.floorService.query().subscribe(
            (res: HttpResponse<IFloor[]>) => {
                this.floors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFloors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFloor) {
        return item.id;
    }

    registerChangeInFloors() {
        this.eventSubscriber = this.eventManager.subscribe('floorListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
