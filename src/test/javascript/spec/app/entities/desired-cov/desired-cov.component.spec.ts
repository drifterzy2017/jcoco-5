/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { DesiredCovComponent } from 'app/entities/desired-cov/desired-cov.component';
import { DesiredCovService } from 'app/entities/desired-cov/desired-cov.service';
import { DesiredCov } from 'app/shared/model/desired-cov.model';

describe('Component Tests', () => {
    describe('DesiredCov Management Component', () => {
        let comp: DesiredCovComponent;
        let fixture: ComponentFixture<DesiredCovComponent>;
        let service: DesiredCovService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DesiredCovComponent],
                providers: []
            })
                .overrideTemplate(DesiredCovComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DesiredCovComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DesiredCovService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DesiredCov(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.desiredCovs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
