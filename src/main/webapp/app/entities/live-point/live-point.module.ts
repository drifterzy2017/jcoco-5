import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    LivePointComponent,
    LivePointDetailComponent,
    LivePointUpdateComponent,
    LivePointDeletePopupComponent,
    LivePointDeleteDialogComponent,
    livePointRoute,
    livePointPopupRoute
} from './';

const ENTITY_STATES = [...livePointRoute, ...livePointPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LivePointComponent,
        LivePointDetailComponent,
        LivePointUpdateComponent,
        LivePointDeleteDialogComponent,
        LivePointDeletePopupComponent
    ],
    entryComponents: [LivePointComponent, LivePointUpdateComponent, LivePointDeleteDialogComponent, LivePointDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5LivePointModule {}
