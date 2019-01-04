import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EventStaticByDay } from 'app/shared/model/event-static-by-day.model';
import { EventStaticByDayService } from './event-static-by-day.service';
import { EventStaticByDayComponent } from './event-static-by-day.component';
import { EventStaticByDayDetailComponent } from './event-static-by-day-detail.component';
import { EventStaticByDayUpdateComponent } from './event-static-by-day-update.component';
import { EventStaticByDayDeletePopupComponent } from './event-static-by-day-delete-dialog.component';
import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';

@Injectable({ providedIn: 'root' })
export class EventStaticByDayResolve implements Resolve<IEventStaticByDay> {
    constructor(private service: EventStaticByDayService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EventStaticByDay> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EventStaticByDay>) => response.ok),
                map((eventStaticByDay: HttpResponse<EventStaticByDay>) => eventStaticByDay.body)
            );
        }
        return of(new EventStaticByDay());
    }
}

export const eventStaticByDayRoute: Routes = [
    {
        path: 'event-static-by-day',
        component: EventStaticByDayComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.eventStaticByDay.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-static-by-day/:id/view',
        component: EventStaticByDayDetailComponent,
        resolve: {
            eventStaticByDay: EventStaticByDayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.eventStaticByDay.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-static-by-day/new',
        component: EventStaticByDayUpdateComponent,
        resolve: {
            eventStaticByDay: EventStaticByDayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.eventStaticByDay.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'event-static-by-day/:id/edit',
        component: EventStaticByDayUpdateComponent,
        resolve: {
            eventStaticByDay: EventStaticByDayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.eventStaticByDay.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const eventStaticByDayPopupRoute: Routes = [
    {
        path: 'event-static-by-day/:id/delete',
        component: EventStaticByDayDeletePopupComponent,
        resolve: {
            eventStaticByDay: EventStaticByDayResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.eventStaticByDay.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
