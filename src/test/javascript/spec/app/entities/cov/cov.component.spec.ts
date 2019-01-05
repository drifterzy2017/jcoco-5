/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { CovComponent } from 'app/entities/cov/cov.component';
import { CovService } from 'app/entities/cov/cov.service';
import { Cov } from 'app/shared/model/cov.model';

describe('Component Tests', () => {
    describe('Cov Management Component', () => {
        let comp: CovComponent;
        let fixture: ComponentFixture<CovComponent>;
        let service: CovService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CovComponent],
                providers: []
            })
                .overrideTemplate(CovComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CovComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CovService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Cov(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.covs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
