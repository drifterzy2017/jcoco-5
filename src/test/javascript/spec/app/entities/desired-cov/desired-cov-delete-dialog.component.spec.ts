/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Jcoco5TestModule } from '../../../test.module';
import { DesiredCovDeleteDialogComponent } from 'app/entities/desired-cov/desired-cov-delete-dialog.component';
import { DesiredCovService } from 'app/entities/desired-cov/desired-cov.service';

describe('Component Tests', () => {
    describe('DesiredCov Management Delete Component', () => {
        let comp: DesiredCovDeleteDialogComponent;
        let fixture: ComponentFixture<DesiredCovDeleteDialogComponent>;
        let service: DesiredCovService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DesiredCovDeleteDialogComponent]
            })
                .overrideTemplate(DesiredCovDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DesiredCovDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DesiredCovService);
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
