/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LiveEventUpdateComponent } from 'app/entities/live-event/live-event-update.component';
import { LiveEventService } from 'app/entities/live-event/live-event.service';
import { LiveEvent } from 'app/shared/model/live-event.model';

describe('Component Tests', () => {
    describe('LiveEvent Management Update Component', () => {
        let comp: LiveEventUpdateComponent;
        let fixture: ComponentFixture<LiveEventUpdateComponent>;
        let service: LiveEventService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LiveEventUpdateComponent]
            })
                .overrideTemplate(LiveEventUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LiveEventUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LiveEventService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new LiveEvent(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.liveEvent = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new LiveEvent();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.liveEvent = entity;
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
