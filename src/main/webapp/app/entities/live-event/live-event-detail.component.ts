import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILiveEvent } from 'app/shared/model/live-event.model';

@Component({
    selector: 'jhi-live-event-detail',
    templateUrl: './live-event-detail.component.html'
})
export class LiveEventDetailComponent implements OnInit {
    liveEvent: ILiveEvent;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ liveEvent }) => {
            this.liveEvent = liveEvent;
        });
    }

    previousState() {
        window.history.back();
    }
}
