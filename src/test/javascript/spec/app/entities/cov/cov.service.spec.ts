/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CovService } from 'app/entities/cov/cov.service';
import { ICov, Cov } from 'app/shared/model/cov.model';

describe('Service Tests', () => {
    describe('Cov Service', () => {
        let injector: TestBed;
        let service: CovService;
        let httpMock: HttpTestingController;
        let elemDefault: ICov;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CovService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Cov(0, 0, 0, 0, 0, currentDate, 'AAAAAAA', 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        birthTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Cov', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        birthTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Cov(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Cov', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        coreSourceId: 1,
                        engineId: 1,
                        qos: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        value: 'BBBBBB',
                        state: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        birthTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Cov', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        coreSourceId: 1,
                        engineId: 1,
                        qos: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        value: 'BBBBBB',
                        state: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthTime: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a Cov', async () => {
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
