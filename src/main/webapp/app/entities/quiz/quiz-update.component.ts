import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQuiz } from 'app/shared/model/quiz.model';
import { QuizService } from './quiz.service';

@Component({
    selector: 'jhi-quiz-update',
    templateUrl: './quiz-update.component.html'
})
export class QuizUpdateComponent implements OnInit {
    quiz: IQuiz;
    isSaving: boolean;
    startTime: string;
    successTime: string;
    useTime: string;

    constructor(protected quizService: QuizService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quiz }) => {
            this.quiz = quiz;
            this.startTime = this.quiz.startTime != null ? this.quiz.startTime.format(DATE_TIME_FORMAT) : null;
            this.successTime = this.quiz.successTime != null ? this.quiz.successTime.format(DATE_TIME_FORMAT) : null;
            this.useTime = this.quiz.useTime != null ? this.quiz.useTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.quiz.startTime = this.startTime != null ? moment(this.startTime, DATE_TIME_FORMAT) : null;
        this.quiz.successTime = this.successTime != null ? moment(this.successTime, DATE_TIME_FORMAT) : null;
        this.quiz.useTime = this.useTime != null ? moment(this.useTime, DATE_TIME_FORMAT) : null;
        if (this.quiz.id !== undefined) {
            this.subscribeToSaveResponse(this.quizService.update(this.quiz));
        } else {
            this.subscribeToSaveResponse(this.quizService.create(this.quiz));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuiz>>) {
        result.subscribe((res: HttpResponse<IQuiz>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
