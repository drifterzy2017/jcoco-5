/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { DesiredCovDetailComponent } from 'app/entities/desired-cov/desired-cov-detail.component';
import { DesiredCov } from 'app/shared/model/desired-cov.model';

describe('Component Tests', () => {
    describe('DesiredCov Management Detail Component', () => {
        let comp: DesiredCovDetailComponent;
        let fixture: ComponentFixture<DesiredCovDetailComponent>;
        const route = ({ data: of({ desiredCov: new DesiredCov(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [DesiredCovDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DesiredCovDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DesiredCovDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.desiredCov).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
