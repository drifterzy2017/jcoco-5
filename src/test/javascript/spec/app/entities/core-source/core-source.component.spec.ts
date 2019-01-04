/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Jcoco5TestModule } from '../../../test.module';
import { CoreSourceComponent } from 'app/entities/core-source/core-source.component';
import { CoreSourceService } from 'app/entities/core-source/core-source.service';
import { CoreSource } from 'app/shared/model/core-source.model';

describe('Component Tests', () => {
    describe('CoreSource Management Component', () => {
        let comp: CoreSourceComponent;
        let fixture: ComponentFixture<CoreSourceComponent>;
        let service: CoreSourceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Jcoco5TestModule],
                declarations: [CoreSourceComponent],
                providers: []
            })
                .overrideTemplate(CoreSourceComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoreSourceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoreSourceService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CoreSource(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.coreSources[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
