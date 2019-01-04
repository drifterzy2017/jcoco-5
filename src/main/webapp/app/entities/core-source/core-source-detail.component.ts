import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoreSource } from 'app/shared/model/core-source.model';

@Component({
    selector: 'jhi-core-source-detail',
    templateUrl: './core-source-detail.component.html'
})
export class CoreSourceDetailComponent implements OnInit {
    coreSource: ICoreSource;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coreSource }) => {
            this.coreSource = coreSource;
        });
    }

    previousState() {
        window.history.back();
    }
}
