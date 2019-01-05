/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DesiredCovUpdateComponent } from 'app/entities/desired-cov/desired-cov-update.component';
import { DesiredCovService } from 'app/entities/desired-cov/desired-cov.service';
import { DesiredCov } from 'app/shared/model/desired-cov.model';

describe('Component Tests', () => {
    describe('DesiredCov Management Update Component', () => {
        let comp: DesiredCovUpdateComponent;
        let fixture: ComponentFixture<DesiredCovUpdateComponent>;
        let service: DesiredCovService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DesiredCovUpdateComponent]
            })
                .overrideTemplate(DesiredCovUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DesiredCovUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DesiredCovService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new DesiredCov(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.desiredCov = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new DesiredCov();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.desiredCov = entity;
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
