/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { FloorUpdateComponent } from 'app/entities/floor/floor-update.component';
import { FloorService } from 'app/entities/floor/floor.service';
import { Floor } from 'app/shared/model/floor.model';

describe('Component Tests', () => {
    describe('Floor Management Update Component', () => {
        let comp: FloorUpdateComponent;
        let fixture: ComponentFixture<FloorUpdateComponent>;
        let service: FloorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [FloorUpdateComponent]
            })
                .overrideTemplate(FloorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FloorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FloorService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Floor(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.floor = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Floor();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.floor = entity;
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
