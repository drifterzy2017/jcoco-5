/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreEventSeverityComponent } from 'app/entities/core-event-severity/core-event-severity.component';
import { CoreEventSeverityService } from 'app/entities/core-event-severity/core-event-severity.service';
import { CoreEventSeverity } from 'app/shared/model/core-event-severity.model';

describe('Component Tests', () => {
    describe('CoreEventSeverity Management Component', () => {
        let comp: CoreEventSeverityComponent;
        let fixture: ComponentFixture<CoreEventSeverityComponent>;
        let service: CoreEventSeverityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreEventSeverityComponent],
                providers: []
            })
                .overrideTemplate(CoreEventSeverityComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoreEventSeverityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoreEventSeverityService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CoreEventSeverity(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.coreEventSeverities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
