import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    PointMaskComponent,
    PointMaskDetailComponent,
    PointMaskUpdateComponent,
    PointMaskDeletePopupComponent,
    PointMaskDeleteDialogComponent,
    pointMaskRoute,
    pointMaskPopupRoute
} from './';

const ENTITY_STATES = [...pointMaskRoute, ...pointMaskPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PointMaskComponent,
        PointMaskDetailComponent,
        PointMaskUpdateComponent,
        PointMaskDeleteDialogComponent,
        PointMaskDeletePopupComponent
    ],
    entryComponents: [PointMaskComponent, PointMaskUpdateComponent, PointMaskDeleteDialogComponent, PointMaskDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5PointMaskModule {}
