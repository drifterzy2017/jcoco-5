/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LibQuestionUpdateComponent } from 'app/entities/lib-question/lib-question-update.component';
import { LibQuestionService } from 'app/entities/lib-question/lib-question.service';
import { LibQuestion } from 'app/shared/model/lib-question.model';

describe('Component Tests', () => {
    describe('LibQuestion Management Update Component', () => {
        let comp: LibQuestionUpdateComponent;
        let fixture: ComponentFixture<LibQuestionUpdateComponent>;
        let service: LibQuestionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LibQuestionUpdateComponent]
            })
                .overrideTemplate(LibQuestionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LibQuestionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LibQuestionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new LibQuestion(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.libQuestion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new LibQuestion();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.libQuestion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
