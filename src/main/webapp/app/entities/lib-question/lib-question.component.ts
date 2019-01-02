import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILibQuestion } from 'app/shared/model/lib-question.model';
import { AccountService } from 'app/core';
import { LibQuestionService } from './lib-question.service';

@Component({
    selector: 'jhi-lib-question',
    templateUrl: './lib-question.component.html'
})
export class LibQuestionComponent implements OnInit, OnDestroy {
    libQuestions: ILibQuestion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected libQuestionService: LibQuestionService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.libQuestionService.query().subscribe(
            (res: HttpResponse<ILibQuestion[]>) => {
                this.libQuestions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLibQuestions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILibQuestion) {
        return item.id;
    }

    registerChangeInLibQuestions() {
        this.eventSubscriber = this.eventManager.subscribe('libQuestionListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
