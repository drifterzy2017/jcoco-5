/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CovUpdateComponent } from 'app/entities/cov/cov-update.component';
import { CovService } from 'app/entities/cov/cov.service';
import { Cov } from 'app/shared/model/cov.model';

describe('Component Tests', () => {
    describe('Cov Management Update Component', () => {
        let comp: CovUpdateComponent;
        let fixture: ComponentFixture<CovUpdateComponent>;
        let service: CovService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CovUpdateComponent]
            })
                .overrideTemplate(CovUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CovUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CovService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cov(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cov = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cov();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cov = entity;
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
