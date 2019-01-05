import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICov } from 'app/shared/model/cov.model';

@Component({
    selector: 'jhi-cov-detail',
    templateUrl: './cov-detail.component.html'
})
export class CovDetailComponent implements OnInit {
    cov: ICov;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cov }) => {
            this.cov = cov;
        });
    }

    previousState() {
        window.history.back();
    }
}
