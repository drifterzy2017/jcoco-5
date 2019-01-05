/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { PointMaskComponent } from 'app/entities/point-mask/point-mask.component';
import { PointMaskService } from 'app/entities/point-mask/point-mask.service';
import { PointMask } from 'app/shared/model/point-mask.model';

describe('Component Tests', () => {
    describe('PointMask Management Component', () => {
        let comp: PointMaskComponent;
        let fixture: ComponentFixture<PointMaskComponent>;
        let service: PointMaskService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [PointMaskComponent],
                providers: []
            })
                .overrideTemplate(PointMaskComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PointMaskComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PointMaskService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PointMask(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pointMasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
