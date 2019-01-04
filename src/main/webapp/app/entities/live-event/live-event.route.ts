import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LiveEvent } from 'app/shared/model/live-event.model';
import { LiveEventService } from './live-event.service';
import { LiveEventComponent } from './live-event.component';
import { LiveEventDetailComponent } from './live-event-detail.component';
import { LiveEventUpdateComponent } from './live-event-update.component';
import { LiveEventDeletePopupComponent } from './live-event-delete-dialog.component';
import { ILiveEvent } from 'app/shared/model/live-event.model';

@Injectable({ providedIn: 'root' })
export class LiveEventResolve implements Resolve<ILiveEvent> {
    constructor(private service: LiveEventService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LiveEvent> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LiveEvent>) => response.ok),
                map((liveEvent: HttpResponse<LiveEvent>) => liveEvent.body)
            );
        }
        return of(new LiveEvent());
    }
}

export const liveEventRoute: Routes = [
    {
        path: 'live-event',
        component: LiveEventComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.liveEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-event/:id/view',
        component: LiveEventDetailComponent,
        resolve: {
            liveEvent: LiveEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.liveEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-event/new',
        component: LiveEventUpdateComponent,
        resolve: {
            liveEvent: LiveEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.liveEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-event/:id/edit',
        component: LiveEventUpdateComponent,
        resolve: {
            liveEvent: LiveEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.liveEvent.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const liveEventPopupRoute: Routes = [
    {
        path: 'live-event/:id/delete',
        component: LiveEventDeletePopupComponent,
        resolve: {
            liveEvent: LiveEventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.liveEvent.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
