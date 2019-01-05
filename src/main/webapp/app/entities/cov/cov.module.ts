import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    CovComponent,
    CovDetailComponent,
    CovUpdateComponent,
    CovDeletePopupComponent,
    CovDeleteDialogComponent,
    covRoute,
    covPopupRoute
} from './';

const ENTITY_STATES = [...covRoute, ...covPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CovComponent, CovDetailComponent, CovUpdateComponent, CovDeleteDialogComponent, CovDeletePopupComponent],
    entryComponents: [CovComponent, CovUpdateComponent, CovDeleteDialogComponent, CovDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5CovModule {}
