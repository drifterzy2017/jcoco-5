/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { PointMaskDetailComponent } from 'app/entities/point-mask/point-mask-detail.component';
import { PointMask } from 'app/shared/model/point-mask.model';

describe('Component Tests', () => {
    describe('PointMask Management Detail Component', () => {
        let comp: PointMaskDetailComponent;
        let fixture: ComponentFixture<PointMaskDetailComponent>;
        const route = ({ data: of({ pointMask: new PointMask(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [PointMaskDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PointMaskDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PointMaskDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pointMask).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
