/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CovDetailComponent } from 'app/entities/cov/cov-detail.component';
import { Cov } from 'app/shared/model/cov.model';

describe('Component Tests', () => {
    describe('Cov Management Detail Component', () => {
        let comp: CovDetailComponent;
        let fixture: ComponentFixture<CovDetailComponent>;
        const route = ({ data: of({ cov: new Cov(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CovDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CovDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CovDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cov).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
