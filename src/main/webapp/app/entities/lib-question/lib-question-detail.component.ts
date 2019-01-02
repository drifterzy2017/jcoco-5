import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILibQuestion } from 'app/shared/model/lib-question.model';

@Component({
    selector: 'jhi-lib-question-detail',
    templateUrl: './lib-question-detail.component.html'
})
export class LibQuestionDetailComponent implements OnInit {
    libQuestion: ILibQuestion;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ libQuestion }) => {
            this.libQuestion = libQuestion;
        });
    }

    previousState() {
        window.history.back();
    }
}
