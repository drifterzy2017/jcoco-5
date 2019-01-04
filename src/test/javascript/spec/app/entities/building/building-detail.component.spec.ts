/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Jcoco5TestModule } from '../../../test.module';
import { BuildingDetailComponent } from 'app/entities/building/building-detail.component';
import { Building } from 'app/shared/model/building.model';

describe('Component Tests', () => {
    describe('Building Management Detail Component', () => {
        let comp: BuildingDetailComponent;
        let fixture: ComponentFixture<BuildingDetailComponent>;
        const route = ({ data: of({ building: new Building(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [BuildingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BuildingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BuildingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.building).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
