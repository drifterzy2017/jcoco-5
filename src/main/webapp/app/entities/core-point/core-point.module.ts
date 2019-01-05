import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    CorePointComponent,
    CorePointDetailComponent,
    CorePointUpdateComponent,
    CorePointDeletePopupComponent,
    CorePointDeleteDialogComponent,
    corePointRoute,
    corePointPopupRoute
} from './';

const ENTITY_STATES = [...corePointRoute, ...corePointPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CorePointComponent,
        CorePointDetailComponent,
        CorePointUpdateComponent,
        CorePointDeleteDialogComponent,
        CorePointDeletePopupComponent
    ],
    entryComponents: [CorePointComponent, CorePointUpdateComponent, CorePointDeleteDialogComponent, CorePointDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5CorePointModule {}
