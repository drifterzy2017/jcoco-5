import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICorePoint } from 'app/shared/model/core-point.model';

@Component({
    selector: 'jhi-core-point-detail',
    templateUrl: './core-point-detail.component.html'
})
export class CorePointDetailComponent implements OnInit {
    corePoint: ICorePoint;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ corePoint }) => {
            this.corePoint = corePoint;
        });
    }

    previousState() {
        window.history.back();
    }
}
