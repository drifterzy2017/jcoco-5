import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Jcoco5BillModule } from './bill/bill.module';
import { Jcoco5LibQuestionModule } from './lib-question/lib-question.module';
import { Jcoco5QuestionModule } from './question/question.module';
import { Jcoco5QuizModule } from './quiz/quiz.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Jcoco5BillModule,
        Jcoco5LibQuestionModule,
        Jcoco5QuestionModule,
        Jcoco5QuizModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5EntityModule {}
