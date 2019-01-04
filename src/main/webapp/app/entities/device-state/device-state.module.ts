import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    DeviceStateComponent,
    DeviceStateDetailComponent,
    DeviceStateUpdateComponent,
    DeviceStateDeletePopupComponent,
    DeviceStateDeleteDialogComponent,
    deviceStateRoute,
    deviceStatePopupRoute
} from './';

const ENTITY_STATES = [...deviceStateRoute, ...deviceStatePopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeviceStateComponent,
        DeviceStateDetailComponent,
        DeviceStateUpdateComponent,
        DeviceStateDeleteDialogComponent,
        DeviceStateDeletePopupComponent
    ],
    entryComponents: [DeviceStateComponent, DeviceStateUpdateComponent, DeviceStateDeleteDialogComponent, DeviceStateDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5DeviceStateModule {}
