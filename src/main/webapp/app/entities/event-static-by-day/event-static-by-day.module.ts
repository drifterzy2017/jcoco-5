import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    EventStaticByDayComponent,
    EventStaticByDayDetailComponent,
    EventStaticByDayUpdateComponent,
    EventStaticByDayDeletePopupComponent,
    EventStaticByDayDeleteDialogComponent,
    eventStaticByDayRoute,
    eventStaticByDayPopupRoute
} from './';

const ENTITY_STATES = [...eventStaticByDayRoute, ...eventStaticByDayPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EventStaticByDayComponent,
        EventStaticByDayDetailComponent,
        EventStaticByDayUpdateComponent,
        EventStaticByDayDeleteDialogComponent,
        EventStaticByDayDeletePopupComponent
    ],
    entryComponents: [
        EventStaticByDayComponent,
        EventStaticByDayUpdateComponent,
        EventStaticByDayDeleteDialogComponent,
        EventStaticByDayDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5EventStaticByDayModule {}
