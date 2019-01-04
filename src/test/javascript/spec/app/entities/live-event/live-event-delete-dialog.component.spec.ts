/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { LiveEventDeleteDialogComponent } from 'app/entities/live-event/live-event-delete-dialog.component';
import { LiveEventService } from 'app/entities/live-event/live-event.service';

describe('Component Tests', () => {
    describe('LiveEvent Management Delete Component', () => {
        let comp: LiveEventDeleteDialogComponent;
        let fixture: ComponentFixture<LiveEventDeleteDialogComponent>;
        let service: LiveEventService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LiveEventDeleteDialogComponent]
            })
                .overrideTemplate(LiveEventDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LiveEventDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LiveEventService);
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
