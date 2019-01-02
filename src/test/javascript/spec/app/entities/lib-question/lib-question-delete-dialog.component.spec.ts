/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { LibQuestionDeleteDialogComponent } from 'app/entities/lib-question/lib-question-delete-dialog.component';
import { LibQuestionService } from 'app/entities/lib-question/lib-question.service';

describe('Component Tests', () => {
    describe('LibQuestion Management Delete Component', () => {
        let comp: LibQuestionDeleteDialogComponent;
        let fixture: ComponentFixture<LibQuestionDeleteDialogComponent>;
        let service: LibQuestionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LibQuestionDeleteDialogComponent]
            })
                .overrideTemplate(LibQuestionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LibQuestionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LibQuestionService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
