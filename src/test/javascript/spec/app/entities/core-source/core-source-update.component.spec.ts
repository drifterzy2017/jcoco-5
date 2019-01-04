/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreSourceUpdateComponent } from 'app/entities/core-source/core-source-update.component';
import { CoreSourceService } from 'app/entities/core-source/core-source.service';
import { CoreSource } from 'app/shared/model/core-source.model';

describe('Component Tests', () => {
    describe('CoreSource Management Update Component', () => {
        let comp: CoreSourceUpdateComponent;
        let fixture: ComponentFixture<CoreSourceUpdateComponent>;
        let service: CoreSourceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreSourceUpdateComponent]
            })
                .overrideTemplate(CoreSourceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoreSourceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoreSourceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CoreSource(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.coreSource = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CoreSource();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.coreSource = entity;
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
