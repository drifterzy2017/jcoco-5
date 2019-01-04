/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreEventSeverityDetailComponent } from 'app/entities/core-event-severity/core-event-severity-detail.component';
import { CoreEventSeverity } from 'app/shared/model/core-event-severity.model';

describe('Component Tests', () => {
    describe('CoreEventSeverity Management Detail Component', () => {
        let comp: CoreEventSeverityDetailComponent;
        let fixture: ComponentFixture<CoreEventSeverityDetailComponent>;
        const route = ({ data: of({ coreEventSeverity: new CoreEventSeverity(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreEventSeverityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CoreEventSeverityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoreEventSeverityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coreEventSeverity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
