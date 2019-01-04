/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { EventStaticByDayUpdateComponent } from 'app/entities/event-static-by-day/event-static-by-day-update.component';
import { EventStaticByDayService } from 'app/entities/event-static-by-day/event-static-by-day.service';
import { EventStaticByDay } from 'app/shared/model/event-static-by-day.model';

describe('Component Tests', () => {
    describe('EventStaticByDay Management Update Component', () => {
        let comp: EventStaticByDayUpdateComponent;
        let fixture: ComponentFixture<EventStaticByDayUpdateComponent>;
        let service: EventStaticByDayService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [EventStaticByDayUpdateComponent]
            })
                .overrideTemplate(EventStaticByDayUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EventStaticByDayUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EventStaticByDayService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new EventStaticByDay(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.eventStaticByDay = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new EventStaticByDay();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.eventStaticByDay = entity;
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
