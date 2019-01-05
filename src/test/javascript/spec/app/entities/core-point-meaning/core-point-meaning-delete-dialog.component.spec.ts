/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointMeaningDeleteDialogComponent } from 'app/entities/core-point-meaning/core-point-meaning-delete-dialog.component';
import { CorePointMeaningService } from 'app/entities/core-point-meaning/core-point-meaning.service';

describe('Component Tests', () => {
    describe('CorePointMeaning Management Delete Component', () => {
        let comp: CorePointMeaningDeleteDialogComponent;
        let fixture: ComponentFixture<CorePointMeaningDeleteDialogComponent>;
        let service: CorePointMeaningService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointMeaningDeleteDialogComponent]
            })
                .overrideTemplate(CorePointMeaningDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CorePointMeaningDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorePointMeaningService);
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
