/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { LiveEventService } from 'app/entities/live-event/live-event.service';
import { ILiveEvent, LiveEvent } from 'app/shared/model/live-event.model';

describe('Service Tests', () => {
    describe('LiveEvent Service', () => {
        let injector: TestBed;
        let service: LiveEventService;
        let httpMock: HttpTestingController;
        let elemDefault: ILiveEvent;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(LiveEventService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new LiveEvent(
                0,
                0,
                currentDate,
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                0,
                'AAAAAAA',
                currentDate,
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        clearTime: currentDate.format(DATE_TIME_FORMAT),
                        confirmTime: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a LiveEvent', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        clearTime: currentDate.format(DATE_TIME_FORMAT),
                        confirmTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthTime: currentDate,
                        clearTime: currentDate,
                        confirmTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new LiveEvent(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a LiveEvent', async () => {
                const returnedFromService = Object.assign(
                    {
                        liveEventId: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        clearedById: 1,
                        clearedByName: 'BBBBBB',
                        clearTime: currentDate.format(DATE_TIME_FORMAT),
                        comment: 'BBBBBB',
                        confirmerId: 1,
                        confirmerName: 'BBBBBB',
                        confirmTime: currentDate.format(DATE_TIME_FORMAT),
                        corePointId: 1,
                        corePointName: 'BBBBBB',
                        coreSourceId: 1,
                        coreSourceName: 'BBBBBB',
                        occurRemark: 'BBBBBB',
                        occurValue: 'BBBBBB',
                        severityId: 1,
                        severityName: 'BBBBBB',
                        stateId: 1,
                        stateName: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        birthTime: currentDate,
                        clearTime: currentDate,
                        confirmTime: currentDate
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

            it('should return a list of LiveEvent', async () => {
                const returnedFromService = Object.assign(
                    {
                        liveEventId: 1,
                        birthTime: currentDate.format(DATE_TIME_FORMAT),
                        clearedById: 1,
                        clearedByName: 'BBBBBB',
                        clearTime: currentDate.format(DATE_TIME_FORMAT),
                        comment: 'BBBBBB',
                        confirmerId: 1,
                        confirmerName: 'BBBBBB',
                        confirmTime: currentDate.format(DATE_TIME_FORMAT),
                        corePointId: 1,
                        corePointName: 'BBBBBB',
                        coreSourceId: 1,
                        coreSourceName: 'BBBBBB',
                        occurRemark: 'BBBBBB',
                        occurValue: 'BBBBBB',
                        severityId: 1,
                        severityName: 'BBBBBB',
                        stateId: 1,
                        stateName: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        birthTime: currentDate,
                        clearTime: currentDate,
                        confirmTime: currentDate
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

            it('should delete a LiveEvent', async () => {
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
