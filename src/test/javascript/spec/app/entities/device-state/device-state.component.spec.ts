/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceStateComponent } from 'app/entities/device-state/device-state.component';
import { DeviceStateService } from 'app/entities/device-state/device-state.service';
import { DeviceState } from 'app/shared/model/device-state.model';

describe('Component Tests', () => {
    describe('DeviceState Management Component', () => {
        let comp: DeviceStateComponent;
        let fixture: ComponentFixture<DeviceStateComponent>;
        let service: DeviceStateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceStateComponent],
                providers: []
            })
                .overrideTemplate(DeviceStateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeviceStateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceStateService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DeviceState(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.deviceStates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
