/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LibQuestionDetailComponent } from 'app/entities/lib-question/lib-question-detail.component';
import { LibQuestion } from 'app/shared/model/lib-question.model';

describe('Component Tests', () => {
    describe('LibQuestion Management Detail Component', () => {
        let comp: LibQuestionDetailComponent;
        let fixture: ComponentFixture<LibQuestionDetailComponent>;
        const route = ({ data: of({ libQuestion: new LibQuestion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LibQuestionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LibQuestionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LibQuestionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.libQuestion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
