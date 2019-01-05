import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPointMask } from 'app/shared/model/point-mask.model';

@Component({
    selector: 'jhi-point-mask-detail',
    templateUrl: './point-mask-detail.component.html'
})
export class PointMaskDetailComponent implements OnInit {
    pointMask: IPointMask;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pointMask }) => {
            this.pointMask = pointMask;
        });
    }

    previousState() {
        window.history.back();
    }
}
