/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { LivePointComponent } from 'app/entities/live-point/live-point.component';
import { LivePointService } from 'app/entities/live-point/live-point.service';
import { LivePoint } from 'app/shared/model/live-point.model';

describe('Component Tests', () => {
    describe('LivePoint Management Component', () => {
        let comp: LivePointComponent;
        let fixture: ComponentFixture<LivePointComponent>;
        let service: LivePointService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LivePointComponent],
                providers: []
            })
                .overrideTemplate(LivePointComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LivePointComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LivePointService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new LivePoint(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.livePoints[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
