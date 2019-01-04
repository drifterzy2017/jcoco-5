/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceStateDeleteDialogComponent } from 'app/entities/device-state/device-state-delete-dialog.component';
import { DeviceStateService } from 'app/entities/device-state/device-state.service';

describe('Component Tests', () => {
    describe('DeviceState Management Delete Component', () => {
        let comp: DeviceStateDeleteDialogComponent;
        let fixture: ComponentFixture<DeviceStateDeleteDialogComponent>;
        let service: DeviceStateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceStateDeleteDialogComponent]
            })
                .overrideTemplate(DeviceStateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceStateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceStateService);
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
