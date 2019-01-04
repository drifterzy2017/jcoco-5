import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    DeviceComponent,
    DeviceDetailComponent,
    DeviceUpdateComponent,
    DeviceDeletePopupComponent,
    DeviceDeleteDialogComponent,
    deviceRoute,
    devicePopupRoute
} from './';

const ENTITY_STATES = [...deviceRoute, ...devicePopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DeviceComponent, DeviceDetailComponent, DeviceUpdateComponent, DeviceDeleteDialogComponent, DeviceDeletePopupComponent],
    entryComponents: [DeviceComponent, DeviceUpdateComponent, DeviceDeleteDialogComponent, DeviceDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5DeviceModule {}
