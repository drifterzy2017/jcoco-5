/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceMaskDetailComponent } from 'app/entities/device-mask/device-mask-detail.component';
import { DeviceMask } from 'app/shared/model/device-mask.model';

describe('Component Tests', () => {
    describe('DeviceMask Management Detail Component', () => {
        let comp: DeviceMaskDetailComponent;
        let fixture: ComponentFixture<DeviceMaskDetailComponent>;
        const route = ({ data: of({ deviceMask: new DeviceMask(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceMaskDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeviceMaskDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceMaskDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.deviceMask).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
