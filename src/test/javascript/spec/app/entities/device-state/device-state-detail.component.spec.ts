/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DeviceStateDetailComponent } from 'app/entities/device-state/device-state-detail.component';
import { DeviceState } from 'app/shared/model/device-state.model';

describe('Component Tests', () => {
    describe('DeviceState Management Detail Component', () => {
        let comp: DeviceStateDetailComponent;
        let fixture: ComponentFixture<DeviceStateDetailComponent>;
        const route = ({ data: of({ deviceState: new DeviceState(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DeviceStateDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeviceStateDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeviceStateDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.deviceState).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
