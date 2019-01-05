import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';

@Component({
    selector: 'jhi-core-point-meaning-detail',
    templateUrl: './core-point-meaning-detail.component.html'
})
export class CorePointMeaningDetailComponent implements OnInit {
    corePointMeaning: ICorePointMeaning;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ corePointMeaning }) => {
            this.corePointMeaning = corePointMeaning;
        });
    }

    previousState() {
        window.history.back();
    }
}
