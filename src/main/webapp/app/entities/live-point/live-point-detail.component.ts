import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILivePoint } from 'app/shared/model/live-point.model';

@Component({
    selector: 'jhi-live-point-detail',
    templateUrl: './live-point-detail.component.html'
})
export class LivePointDetailComponent implements OnInit {
    livePoint: ILivePoint;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ livePoint }) => {
            this.livePoint = livePoint;
        });
    }

    previousState() {
        window.history.back();
    }
}
