/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { LiveEventComponent } from 'app/entities/live-event/live-event.component';
import { LiveEventService } from 'app/entities/live-event/live-event.service';
import { LiveEvent } from 'app/shared/model/live-event.model';

describe('Component Tests', () => {
    describe('LiveEvent Management Component', () => {
        let comp: LiveEventComponent;
        let fixture: ComponentFixture<LiveEventComponent>;
        let service: LiveEventService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LiveEventComponent],
                providers: []
            })
                .overrideTemplate(LiveEventComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LiveEventComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LiveEventService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new LiveEvent(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.liveEvents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
