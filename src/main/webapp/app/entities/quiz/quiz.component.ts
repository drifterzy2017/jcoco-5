import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IQuiz } from 'app/shared/model/quiz.model';
import { AccountService } from 'app/core';
import { QuizService } from './quiz.service';

@Component({
    selector: 'jhi-quiz',
    templateUrl: './quiz.component.html'
})
export class QuizComponent implements OnInit, OnDestroy {
    quizzes: IQuiz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected quizService: QuizService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.quizService.query().subscribe(
            (res: HttpResponse<IQuiz[]>) => {
                this.quizzes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInQuizzes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IQuiz) {
        return item.id;
    }

    registerChangeInQuizzes() {
        this.eventSubscriber = this.eventManager.subscribe('quizListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
