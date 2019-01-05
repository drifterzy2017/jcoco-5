import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDesiredCov } from 'app/shared/model/desired-cov.model';

@Component({
    selector: 'jhi-desired-cov-detail',
    templateUrl: './desired-cov-detail.component.html'
})
export class DesiredCovDetailComponent implements OnInit {
    desiredCov: IDesiredCov;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ desiredCov }) => {
            this.desiredCov = desiredCov;
        });
    }

    previousState() {
        window.history.back();
    }
}
