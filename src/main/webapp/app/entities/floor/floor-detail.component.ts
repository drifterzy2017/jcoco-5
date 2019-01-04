import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFloor } from 'app/shared/model/floor.model';

@Component({
    selector: 'jhi-floor-detail',
    templateUrl: './floor-detail.component.html'
})
export class FloorDetailComponent implements OnInit {
    floor: IFloor;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ floor }) => {
            this.floor = floor;
        });
    }

    previousState() {
        window.history.back();
    }
}
