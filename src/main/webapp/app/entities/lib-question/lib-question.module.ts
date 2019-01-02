import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Jcoco5SharedModule } from 'app/shared';
import {
    LibQuestionComponent,
    LibQuestionDetailComponent,
    LibQuestionUpdateComponent,
    LibQuestionDeletePopupComponent,
    LibQuestionDeleteDialogComponent,
    libQuestionRoute,
    libQuestionPopupRoute
} from './';

const ENTITY_STATES = [...libQuestionRoute, ...libQuestionPopupRoute];

@NgModule({
    imports: [Jcoco5SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LibQuestionComponent,
        LibQuestionDetailComponent,
        LibQuestionUpdateComponent,
        LibQuestionDeleteDialogComponent,
        LibQuestionDeletePopupComponent
    ],
    entryComponents: [LibQuestionComponent, LibQuestionUpdateComponent, LibQuestionDeleteDialogComponent, LibQuestionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Jcoco5LibQuestionModule {}
