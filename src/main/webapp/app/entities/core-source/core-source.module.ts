import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    CoreSourceComponent,
    CoreSourceDetailComponent,
    CoreSourceUpdateComponent,
    CoreSourceDeletePopupComponent,
    CoreSourceDeleteDialogComponent,
    coreSourceRoute,
    coreSourcePopupRoute
} from './';

const ENTITY_STATES = [...coreSourceRoute, ...coreSourcePopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CoreSourceComponent,
        CoreSourceDetailComponent,
        CoreSourceUpdateComponent,
        CoreSourceDeleteDialogComponent,
        CoreSourceDeletePopupComponent
    ],
    entryComponents: [CoreSourceComponent, CoreSourceUpdateComponent, CoreSourceDeleteDialogComponent, CoreSourceDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5CoreSourceModule {}
