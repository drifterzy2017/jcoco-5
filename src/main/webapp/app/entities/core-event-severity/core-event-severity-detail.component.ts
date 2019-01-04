import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';

@Component({
    selector: 'jhi-core-event-severity-detail',
    templateUrl: './core-event-severity-detail.component.html'
})
export class CoreEventSeverityDetailComponent implements OnInit {
    coreEventSeverity: ICoreEventSeverity;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coreEventSeverity }) => {
            this.coreEventSeverity = coreEventSeverity;
        });
    }

    previousState() {
        window.history.back();
    }
}
