/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CorePointService } from 'app/entities/core-point/core-point.service';
import { ICorePoint, CorePoint } from 'app/shared/model/core-point.model';

describe('Service Tests', () => {
    describe('CorePoint Service', () => {
        let injector: TestBed;
        let service: CorePointService;
        let httpMock: HttpTestingController;
        let elemDefault: ICorePoint;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CorePointService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new CorePoint(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                0,
                0,
                false,
                false,
                'AAAAAAA',
                0,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CorePoint', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new CorePoint(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CorePoint', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        pointName: 'BBBBBB',
                        accuracy: 'BBBBBB',
                        unit: 'BBBBBB',
                        max: 'BBBBBB',
                        min: 'BBBBBB',
                        coreSourceId: 1,
                        coreDataTypeId: 1,
                        eventSeverity: 1,
                        stateRuleId: 1,
                        readable: true,
                        writable: true,
                        defaultValue: 'BBBBBB',
                        step: 1,
                        masked: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CorePoint', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        pointName: 'BBBBBB',
                        accuracy: 'BBBBBB',
                        unit: 'BBBBBB',
                        max: 'BBBBBB',
                        min: 'BBBBBB',
                        coreSourceId: 1,
                        coreDataTypeId: 1,
                        eventSeverity: 1,
                        stateRuleId: 1,
                        readable: true,
                        writable: true,
                        defaultValue: 'BBBBBB',
                        step: 1,
                        masked: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CorePoint', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
