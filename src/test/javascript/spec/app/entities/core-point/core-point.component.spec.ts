/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointComponent } from 'app/entities/core-point/core-point.component';
import { CorePointService } from 'app/entities/core-point/core-point.service';
import { CorePoint } from 'app/shared/model/core-point.model';

describe('Component Tests', () => {
    describe('CorePoint Management Component', () => {
        let comp: CorePointComponent;
        let fixture: ComponentFixture<CorePointComponent>;
        let service: CorePointService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointComponent],
                providers: []
            })
                .overrideTemplate(CorePointComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorePointComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorePointService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CorePoint(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.corePoints[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
