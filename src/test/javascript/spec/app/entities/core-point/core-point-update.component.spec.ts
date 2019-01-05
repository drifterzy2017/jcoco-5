/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointUpdateComponent } from 'app/entities/core-point/core-point-update.component';
import { CorePointService } from 'app/entities/core-point/core-point.service';
import { CorePoint } from 'app/shared/model/core-point.model';

describe('Component Tests', () => {
    describe('CorePoint Management Update Component', () => {
        let comp: CorePointUpdateComponent;
        let fixture: ComponentFixture<CorePointUpdateComponent>;
        let service: CorePointService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointUpdateComponent]
            })
                .overrideTemplate(CorePointUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorePointUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorePointService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CorePoint(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.corePoint = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CorePoint();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.corePoint = entity;
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
