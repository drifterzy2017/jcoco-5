/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { EventStaticByDayDeleteDialogComponent } from 'app/entities/event-static-by-day/event-static-by-day-delete-dialog.component';
import { EventStaticByDayService } from 'app/entities/event-static-by-day/event-static-by-day.service';

describe('Component Tests', () => {
    describe('EventStaticByDay Management Delete Component', () => {
        let comp: EventStaticByDayDeleteDialogComponent;
        let fixture: ComponentFixture<EventStaticByDayDeleteDialogComponent>;
        let service: EventStaticByDayService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [EventStaticByDayDeleteDialogComponent]
            })
                .overrideTemplate(EventStaticByDayDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventStaticByDayDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventStaticByDayService);
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
