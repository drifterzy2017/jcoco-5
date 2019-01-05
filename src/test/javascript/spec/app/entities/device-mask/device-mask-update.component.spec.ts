/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceMaskUpdateComponent } from 'app/entities/device-mask/device-mask-update.component';
import { DeviceMaskService } from 'app/entities/device-mask/device-mask.service';
import { DeviceMask } from 'app/shared/model/device-mask.model';

describe('Component Tests', () => {
    describe('DeviceMask Management Update Component', () => {
        let comp: DeviceMaskUpdateComponent;
        let fixture: ComponentFixture<DeviceMaskUpdateComponent>;
        let service: DeviceMaskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceMaskUpdateComponent]
            })
                .overrideTemplate(DeviceMaskUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeviceMaskUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceMaskService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DeviceMask(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.deviceMask = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DeviceMask();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.deviceMask = entity;
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
