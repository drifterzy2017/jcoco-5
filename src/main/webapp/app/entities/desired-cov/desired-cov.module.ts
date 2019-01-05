import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    DesiredCovComponent,
    DesiredCovDetailComponent,
    DesiredCovUpdateComponent,
    DesiredCovDeletePopupComponent,
    DesiredCovDeleteDialogComponent,
    desiredCovRoute,
    desiredCovPopupRoute
} from './';

const ENTITY_STATES = [...desiredCovRoute, ...desiredCovPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DesiredCovComponent,
        DesiredCovDetailComponent,
        DesiredCovUpdateComponent,
        DesiredCovDeleteDialogComponent,
        DesiredCovDeletePopupComponent
    ],
    entryComponents: [DesiredCovComponent, DesiredCovUpdateComponent, DesiredCovDeleteDialogComponent, DesiredCovDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5DesiredCovModule {}
