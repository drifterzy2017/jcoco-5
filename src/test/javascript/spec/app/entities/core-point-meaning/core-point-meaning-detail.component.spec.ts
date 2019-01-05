/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointMeaningDetailComponent } from 'app/entities/core-point-meaning/core-point-meaning-detail.component';
import { CorePointMeaning } from 'app/shared/model/core-point-meaning.model';

describe('Component Tests', () => {
    describe('CorePointMeaning Management Detail Component', () => {
        let comp: CorePointMeaningDetailComponent;
        let fixture: ComponentFixture<CorePointMeaningDetailComponent>;
        const route = ({ data: of({ corePointMeaning: new CorePointMeaning(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointMeaningDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CorePointMeaningDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CorePointMeaningDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.corePointMeaning).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
