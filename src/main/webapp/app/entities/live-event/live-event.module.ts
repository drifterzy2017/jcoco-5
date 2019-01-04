import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    LiveEventComponent,
    LiveEventDetailComponent,
    LiveEventUpdateComponent,
    LiveEventDeletePopupComponent,
    LiveEventDeleteDialogComponent,
    liveEventRoute,
    liveEventPopupRoute
} from './';

const ENTITY_STATES = [...liveEventRoute, ...liveEventPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LiveEventComponent,
        LiveEventDetailComponent,
        LiveEventUpdateComponent,
        LiveEventDeleteDialogComponent,
        LiveEventDeletePopupComponent
    ],
    entryComponents: [LiveEventComponent, LiveEventUpdateComponent, LiveEventDeleteDialogComponent, LiveEventDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5LiveEventModule {}
