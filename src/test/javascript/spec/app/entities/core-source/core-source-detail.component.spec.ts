/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreSourceDetailComponent } from 'app/entities/core-source/core-source-detail.component';
import { CoreSource } from 'app/shared/model/core-source.model';

describe('Component Tests', () => {
    describe('CoreSource Management Detail Component', () => {
        let comp: CoreSourceDetailComponent;
        let fixture: ComponentFixture<CoreSourceDetailComponent>;
        const route = ({ data: of({ coreSource: new CoreSource(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreSourceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CoreSourceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoreSourceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coreSource).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
