import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LivePoint } from 'app/shared/model/live-point.model';
import { LivePointService } from './live-point.service';
import { LivePointComponent } from './live-point.component';
import { LivePointDetailComponent } from './live-point-detail.component';
import { LivePointUpdateComponent } from './live-point-update.component';
import { LivePointDeletePopupComponent } from './live-point-delete-dialog.component';
import { ILivePoint } from 'app/shared/model/live-point.model';

@Injectable({ providedIn: 'root' })
export class LivePointResolve implements Resolve<ILivePoint> {
    constructor(private service: LivePointService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LivePoint> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LivePoint>) => response.ok),
                map((livePoint: HttpResponse<LivePoint>) => livePoint.body)
            );
        }
        return of(new LivePoint());
    }
}

export const livePointRoute: Routes = [
    {
        path: 'live-point',
        component: LivePointComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.livePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-point/:id/view',
        component: LivePointDetailComponent,
        resolve: {
            livePoint: LivePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.livePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-point/new',
        component: LivePointUpdateComponent,
        resolve: {
            livePoint: LivePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.livePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'live-point/:id/edit',
        component: LivePointUpdateComponent,
        resolve: {
            livePoint: LivePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.livePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const livePointPopupRoute: Routes = [
    {
        path: 'live-point/:id/delete',
        component: LivePointDeletePopupComponent,
        resolve: {
            livePoint: LivePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.livePoint.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
