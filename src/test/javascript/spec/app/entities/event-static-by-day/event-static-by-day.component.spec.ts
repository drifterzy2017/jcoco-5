/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { EventStaticByDayComponent } from 'app/entities/event-static-by-day/event-static-by-day.component';
import { EventStaticByDayService } from 'app/entities/event-static-by-day/event-static-by-day.service';
import { EventStaticByDay } from 'app/shared/model/event-static-by-day.model';

describe('Component Tests', () => {
    describe('EventStaticByDay Management Component', () => {
        let comp: EventStaticByDayComponent;
        let fixture: ComponentFixture<EventStaticByDayComponent>;
        let service: EventStaticByDayService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [EventStaticByDayComponent],
                providers: []
            })
                .overrideTemplate(EventStaticByDayComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EventStaticByDayComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventStaticByDayService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EventStaticByDay(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.eventStaticByDays[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
