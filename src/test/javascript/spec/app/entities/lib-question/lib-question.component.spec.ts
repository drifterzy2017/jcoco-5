/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { LibQuestionComponent } from 'app/entities/lib-question/lib-question.component';
import { LibQuestionService } from 'app/entities/lib-question/lib-question.service';
import { LibQuestion } from 'app/shared/model/lib-question.model';

describe('Component Tests', () => {
    describe('LibQuestion Management Component', () => {
        let comp: LibQuestionComponent;
        let fixture: ComponentFixture<LibQuestionComponent>;
        let service: LibQuestionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LibQuestionComponent],
                providers: []
            })
                .overrideTemplate(LibQuestionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LibQuestionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LibQuestionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new LibQuestion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.libQuestions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
