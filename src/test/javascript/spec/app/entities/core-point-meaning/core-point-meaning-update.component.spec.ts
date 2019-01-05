/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointMeaningUpdateComponent } from 'app/entities/core-point-meaning/core-point-meaning-update.component';
import { CorePointMeaningService } from 'app/entities/core-point-meaning/core-point-meaning.service';
import { CorePointMeaning } from 'app/shared/model/core-point-meaning.model';

describe('Component Tests', () => {
    describe('CorePointMeaning Management Update Component', () => {
        let comp: CorePointMeaningUpdateComponent;
        let fixture: ComponentFixture<CorePointMeaningUpdateComponent>;
        let service: CorePointMeaningService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointMeaningUpdateComponent]
            })
                .overrideTemplate(CorePointMeaningUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorePointMeaningUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorePointMeaningService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CorePointMeaning(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.corePointMeaning = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CorePointMeaning();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.corePointMeaning = entity;
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
