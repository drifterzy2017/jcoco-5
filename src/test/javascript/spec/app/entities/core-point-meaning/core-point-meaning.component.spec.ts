/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { CorePointMeaningComponent } from 'app/entities/core-point-meaning/core-point-meaning.component';
import { CorePointMeaningService } from 'app/entities/core-point-meaning/core-point-meaning.service';
import { CorePointMeaning } from 'app/shared/model/core-point-meaning.model';

describe('Component Tests', () => {
    describe('CorePointMeaning Management Component', () => {
        let comp: CorePointMeaningComponent;
        let fixture: ComponentFixture<CorePointMeaningComponent>;
        let service: CorePointMeaningService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CorePointMeaningComponent],
                providers: []
            })
                .overrideTemplate(CorePointMeaningComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CorePointMeaningComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CorePointMeaningService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CorePointMeaning(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.corePointMeanings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
