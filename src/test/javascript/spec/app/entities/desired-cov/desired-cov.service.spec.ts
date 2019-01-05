/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DesiredCovService } from 'app/entities/desired-cov/desired-cov.service';
import { IDesiredCov, DesiredCov } from 'app/shared/model/desired-cov.model';

describe('Service Tests', () => {
    describe('DesiredCov Service', () => {
        let injector: TestBed;
        let service: DesiredCovService;
        let httpMock: HttpTestingController;
        let elemDefault: IDesiredCov;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DesiredCovService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new DesiredCov(0, 0, 0, 0, 'AAAAAAA', 0, currentDate, 0, 'AAAAAAA');
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

            it('should create a DesiredCov', async () => {
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
                    .create(new DesiredCov(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DesiredCov', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        coreSourceId: 1,
                        engineId: 1,
                        desiredValue: 'BBBBBB',
                        userId: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        state: 1,
                        message: 'BBBBBB'
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

            it('should return a list of DesiredCov', async () => {
                const returnedFromService = Object.assign(
                    {
                        corePointId: 1,
                        coreSourceId: 1,
                        engineId: 1,
                        desiredValue: 'BBBBBB',
                        userId: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        state: 1,
                        message: 'BBBBBB'
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

            it('should delete a DesiredCov', async () => {
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
