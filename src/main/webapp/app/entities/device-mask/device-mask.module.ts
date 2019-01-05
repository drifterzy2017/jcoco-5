import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    DeviceMaskComponent,
    DeviceMaskDetailComponent,
    DeviceMaskUpdateComponent,
    DeviceMaskDeletePopupComponent,
    DeviceMaskDeleteDialogComponent,
    deviceMaskRoute,
    deviceMaskPopupRoute
} from './';

const ENTITY_STATES = [...deviceMaskRoute, ...deviceMaskPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeviceMaskComponent,
        DeviceMaskDetailComponent,
        DeviceMaskUpdateComponent,
        DeviceMaskDeleteDialogComponent,
        DeviceMaskDeletePopupComponent
    ],
    entryComponents: [DeviceMaskComponent, DeviceMaskUpdateComponent, DeviceMaskDeleteDialogComponent, DeviceMaskDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5DeviceMaskModule {}
