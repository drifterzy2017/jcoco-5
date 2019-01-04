/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceStateUpdateComponent } from 'app/entities/device-state/device-state-update.component';
import { DeviceStateService } from 'app/entities/device-state/device-state.service';
import { DeviceState } from 'app/shared/model/device-state.model';

describe('Component Tests', () => {
    describe('DeviceState Management Update Component', () => {
        let comp: DeviceStateUpdateComponent;
        let fixture: ComponentFixture<DeviceStateUpdateComponent>;
        let service: DeviceStateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceStateUpdateComponent]
            })
                .overrideTemplate(DeviceStateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeviceStateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceStateService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DeviceState(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.deviceState = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DeviceState();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.deviceState = entity;
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
