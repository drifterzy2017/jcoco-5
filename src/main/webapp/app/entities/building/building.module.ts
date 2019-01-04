import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    BuildingComponent,
    BuildingDetailComponent,
    BuildingUpdateComponent,
    BuildingDeletePopupComponent,
    BuildingDeleteDialogComponent,
    buildingRoute,
    buildingPopupRoute
} from './';

const ENTITY_STATES = [...buildingRoute, ...buildingPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BuildingComponent,
        BuildingDetailComponent,
        BuildingUpdateComponent,
        BuildingDeleteDialogComponent,
        BuildingDeletePopupComponent
    ],
    entryComponents: [BuildingComponent, BuildingUpdateComponent, BuildingDeleteDialogComponent, BuildingDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5BuildingModule {}
