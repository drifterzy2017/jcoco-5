import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';

@Component({
    selector: 'jhi-event-static-by-day-detail',
    templateUrl: './event-static-by-day-detail.component.html'
})
export class EventStaticByDayDetailComponent implements OnInit {
    eventStaticByDay: IEventStaticByDay;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventStaticByDay }) => {
            this.eventStaticByDay = eventStaticByDay;
        });
    }

    previousState() {
        window.history.back();
    }
}
