/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointDetailComponent } from 'app/entities/core-point/core-point-detail.component';
import { CorePoint } from 'app/shared/model/core-point.model';

describe('Component Tests', () => {
    describe('CorePoint Management Detail Component', () => {
        let comp: CorePointDetailComponent;
        let fixture: ComponentFixture<CorePointDetailComponent>;
        const route = ({ data: of({ corePoint: new CorePoint(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CorePointDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CorePointDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.corePoint).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
