/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { ParkComponent } from 'app/entities/park/park.component';
import { ParkService } from 'app/entities/park/park.service';
import { Park } from 'app/shared/model/park.model';

describe('Component Tests', () => {
    describe('Park Management Component', () => {
        let comp: ParkComponent;
        let fixture: ComponentFixture<ParkComponent>;
        let service: ParkService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [ParkComponent],
                providers: []
            })
                .overrideTemplate(ParkComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ParkComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParkService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Park(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.parks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
