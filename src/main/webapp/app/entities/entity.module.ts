import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Jcoco5BillModule } from './bill/bill.module';
import { Jcoco5LibQuestionModule } from './lib-question/lib-question.module';
import { Jcoco5QuestionModule } from './question/question.module';
import { Jcoco5QuizModule } from './quiz/quiz.module';
import { Jcoco5LiveEventModule } from './live-event/live-event.module';
import { Jcoco5CoreEventSeverityModule } from './core-event-severity/core-event-severity.module';
import { Jcoco5DeviceModule } from './device/device.module';
import { Jcoco5DeviceStateModule } from './device-state/device-state.module';
import { Jcoco5ParkModule } from './park/park.module';
import { Jcoco5RoomModule } from './room/room.module';
import { Jcoco5FloorModule } from './floor/floor.module';
import { Jcoco5BuildingModule } from './building/building.module';
import { Jcoco5CoreSourceModule } from './core-source/core-source.module';
import { Jcoco5EventStaticByDayModule } from './event-static-by-day/event-static-by-day.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Jcoco5BillModule,
        Jcoco5LibQuestionModule,
        Jcoco5QuestionModule,
        Jcoco5QuizModule,
        Jcoco5LiveEventModule,
        Jcoco5CoreEventSeverityModule,
        Jcoco5DeviceModule,
        Jcoco5DeviceStateModule,
        Jcoco5ParkModule,
        Jcoco5RoomModule,
        Jcoco5FloorModule,
        Jcoco5BuildingModule,
        Jcoco5CoreSourceModule,
        Jcoco5EventStaticByDayModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5EntityModule {}
