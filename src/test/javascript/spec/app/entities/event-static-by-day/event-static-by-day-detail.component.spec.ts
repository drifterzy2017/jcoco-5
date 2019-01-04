/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { EventStaticByDayDetailComponent } from 'app/entities/event-static-by-day/event-static-by-day-detail.component';
import { EventStaticByDay } from 'app/shared/model/event-static-by-day.model';

describe('Component Tests', () => {
    describe('EventStaticByDay Management Detail Component', () => {
        let comp: EventStaticByDayDetailComponent;
        let fixture: ComponentFixture<EventStaticByDayDetailComponent>;
        const route = ({ data: of({ eventStaticByDay: new EventStaticByDay(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [EventStaticByDayDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EventStaticByDayDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EventStaticByDayDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eventStaticByDay).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
