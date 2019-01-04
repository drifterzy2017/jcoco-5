import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    CoreEventSeverityComponent,
    CoreEventSeverityDetailComponent,
    CoreEventSeverityUpdateComponent,
    CoreEventSeverityDeletePopupComponent,
    CoreEventSeverityDeleteDialogComponent,
    coreEventSeverityRoute,
    coreEventSeverityPopupRoute
} from './';

const ENTITY_STATES = [...coreEventSeverityRoute, ...coreEventSeverityPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CoreEventSeverityComponent,
        CoreEventSeverityDetailComponent,
        CoreEventSeverityUpdateComponent,
        CoreEventSeverityDeleteDialogComponent,
        CoreEventSeverityDeletePopupComponent
    ],
    entryComponents: [
        CoreEventSeverityComponent,
        CoreEventSeverityUpdateComponent,
        CoreEventSeverityDeleteDialogComponent,
        CoreEventSeverityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5CoreEventSeverityModule {}
