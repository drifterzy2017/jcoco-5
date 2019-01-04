/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { LiveEventDetailComponent } from 'app/entities/live-event/live-event-detail.component';
import { LiveEvent } from 'app/shared/model/live-event.model';

describe('Component Tests', () => {
    describe('LiveEvent Management Detail Component', () => {
        let comp: LiveEventDetailComponent;
        let fixture: ComponentFixture<LiveEventDetailComponent>;
        const route = ({ data: of({ liveEvent: new LiveEvent(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [LiveEventDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LiveEventDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LiveEventDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.liveEvent).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
