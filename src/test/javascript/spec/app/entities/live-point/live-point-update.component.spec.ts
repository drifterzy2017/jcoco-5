/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LivePointUpdateComponent } from 'app/entities/live-point/live-point-update.component';
import { LivePointService } from 'app/entities/live-point/live-point.service';
import { LivePoint } from 'app/shared/model/live-point.model';

describe('Component Tests', () => {
    describe('LivePoint Management Update Component', () => {
        let comp: LivePointUpdateComponent;
        let fixture: ComponentFixture<LivePointUpdateComponent>;
        let service: LivePointService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LivePointUpdateComponent]
            })
                .overrideTemplate(LivePointUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LivePointUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivePointService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new LivePoint(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.livePoint = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new LivePoint();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.livePoint = entity;
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
