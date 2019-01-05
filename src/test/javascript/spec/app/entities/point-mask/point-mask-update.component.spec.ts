/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { PointMaskUpdateComponent } from 'app/entities/point-mask/point-mask-update.component';
import { PointMaskService } from 'app/entities/point-mask/point-mask.service';
import { PointMask } from 'app/shared/model/point-mask.model';

describe('Component Tests', () => {
    describe('PointMask Management Update Component', () => {
        let comp: PointMaskUpdateComponent;
        let fixture: ComponentFixture<PointMaskUpdateComponent>;
        let service: PointMaskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [PointMaskUpdateComponent]
            })
                .overrideTemplate(PointMaskUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PointMaskUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PointMaskService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PointMask(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pointMask = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PointMask();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pointMask = entity;
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
