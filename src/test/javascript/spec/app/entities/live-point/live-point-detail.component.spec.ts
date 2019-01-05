/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LivePointDetailComponent } from 'app/entities/live-point/live-point-detail.component';
import { LivePoint } from 'app/shared/model/live-point.model';

describe('Component Tests', () => {
    describe('LivePoint Management Detail Component', () => {
        let comp: LivePointDetailComponent;
        let fixture: ComponentFixture<LivePointDetailComponent>;
        const route = ({ data: of({ livePoint: new LivePoint(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LivePointDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LivePointDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LivePointDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.livePoint).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
