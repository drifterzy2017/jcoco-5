/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreEventSeverityUpdateComponent } from 'app/entities/core-event-severity/core-event-severity-update.component';
import { CoreEventSeverityService } from 'app/entities/core-event-severity/core-event-severity.service';
import { CoreEventSeverity } from 'app/shared/model/core-event-severity.model';

describe('Component Tests', () => {
    describe('CoreEventSeverity Management Update Component', () => {
        let comp: CoreEventSeverityUpdateComponent;
        let fixture: ComponentFixture<CoreEventSeverityUpdateComponent>;
        let service: CoreEventSeverityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreEventSeverityUpdateComponent]
            })
                .overrideTemplate(CoreEventSeverityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoreEventSeverityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoreEventSeverityService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CoreEventSeverity(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.coreEventSeverity = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CoreEventSeverity();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.coreEventSeverity = entity;
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
