import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ILibQuestion } from 'app/shared/model/lib-question.model';
import { LibQuestionService } from './lib-question.service';

@Component({
    selector: 'jhi-lib-question-update',
    templateUrl: './lib-question-update.component.html'
})
export class LibQuestionUpdateComponent implements OnInit {
    libQuestion: ILibQuestion;
    isSaving: boolean;

    constructor(protected libQuestionService: LibQuestionService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ libQuestion }) => {
            this.libQuestion = libQuestion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.libQuestion.id !== undefined) {
            this.subscribeToSaveResponse(this.libQuestionService.update(this.libQuestion));
        } else {
            this.subscribeToSaveResponse(this.libQuestionService.create(this.libQuestion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILibQuestion>>) {
        result.subscribe((res: HttpResponse<ILibQuestion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
