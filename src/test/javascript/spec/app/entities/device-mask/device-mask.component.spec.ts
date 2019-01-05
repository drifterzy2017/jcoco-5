/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceMaskComponent } from 'app/entities/device-mask/device-mask.component';
import { DeviceMaskService } from 'app/entities/device-mask/device-mask.service';
import { DeviceMask } from 'app/shared/model/device-mask.model';

describe('Component Tests', () => {
    describe('DeviceMask Management Component', () => {
        let comp: DeviceMaskComponent;
        let fixture: ComponentFixture<DeviceMaskComponent>;
        let service: DeviceMaskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceMaskComponent],
                providers: []
            })
                .overrideTemplate(DeviceMaskComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeviceMaskComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeviceMaskService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DeviceMask(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.deviceMasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
