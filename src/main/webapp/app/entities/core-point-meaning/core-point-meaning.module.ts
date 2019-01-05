import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    CorePointMeaningComponent,
    CorePointMeaningDetailComponent,
    CorePointMeaningUpdateComponent,
    CorePointMeaningDeletePopupComponent,
    CorePointMeaningDeleteDialogComponent,
    corePointMeaningRoute,
    corePointMeaningPopupRoute
} from './';

const ENTITY_STATES = [...corePointMeaningRoute, ...corePointMeaningPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CorePointMeaningComponent,
        CorePointMeaningDetailComponent,
        CorePointMeaningUpdateComponent,
        CorePointMeaningDeleteDialogComponent,
        CorePointMeaningDeletePopupComponent
    ],
    entryComponents: [
        CorePointMeaningComponent,
        CorePointMeaningUpdateComponent,
        CorePointMeaningDeleteDialogComponent,
        CorePointMeaningDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5CorePointMeaningModule {}
