/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceMaskDeleteDialogComponent } from 'app/entities/device-mask/device-mask-delete-dialog.component';
import { DeviceMaskService } from 'app/entities/device-mask/device-mask.service';

describe('Component Tests', () => {
    describe('DeviceMask Management Delete Component', () => {
        let comp: DeviceMaskDeleteDialogComponent;
        let fixture: ComponentFixture<DeviceMaskDeleteDialogComponent>;
        let service: DeviceMaskService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceMaskDeleteDialogComponent]
            })
                .overrideTemplate(DeviceMaskDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceMaskDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceMaskService);
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
