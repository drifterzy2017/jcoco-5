import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    ParkComponent,
    ParkDetailComponent,
    ParkUpdateComponent,
    ParkDeletePopupComponent,
    ParkDeleteDialogComponent,
    parkRoute,
    parkPopupRoute
} from './';

const ENTITY_STATES = [...parkRoute, ...parkPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ParkComponent, ParkDetailComponent, ParkUpdateComponent, ParkDeleteDialogComponent, ParkDeletePopupComponent],
    entryComponents: [ParkComponent, ParkUpdateComponent, ParkDeleteDialogComponent, ParkDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5ParkModule {}
