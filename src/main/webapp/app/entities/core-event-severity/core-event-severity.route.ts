import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CoreEventSeverity } from 'app/shared/model/core-event-severity.model';
import { CoreEventSeverityService } from './core-event-severity.service';
import { CoreEventSeverityComponent } from './core-event-severity.component';
import { CoreEventSeverityDetailComponent } from './core-event-severity-detail.component';
import { CoreEventSeverityUpdateComponent } from './core-event-severity-update.component';
import { CoreEventSeverityDeletePopupComponent } from './core-event-severity-delete-dialog.component';
import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';

@Injectable({ providedIn: 'root' })
export class CoreEventSeverityResolve implements Resolve<ICoreEventSeverity> {
    constructor(private service: CoreEventSeverityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CoreEventSeverity> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CoreEventSeverity>) => response.ok),
                map((coreEventSeverity: HttpResponse<CoreEventSeverity>) => coreEventSeverity.body)
            );
        }
        return of(new CoreEventSeverity());
    }
}

export const coreEventSeverityRoute: Routes = [
    {
        path: 'core-event-severity',
        component: CoreEventSeverityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreEventSeverity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-event-severity/:id/view',
        component: CoreEventSeverityDetailComponent,
        resolve: {
            coreEventSeverity: CoreEventSeverityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreEventSeverity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-event-severity/new',
        component: CoreEventSeverityUpdateComponent,
        resolve: {
            coreEventSeverity: CoreEventSeverityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreEventSeverity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-event-severity/:id/edit',
        component: CoreEventSeverityUpdateComponent,
        resolve: {
            coreEventSeverity: CoreEventSeverityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreEventSeverity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coreEventSeverityPopupRoute: Routes = [
    {
        path: 'core-event-severity/:id/delete',
        component: CoreEventSeverityDeletePopupComponent,
        resolve: {
            coreEventSeverity: CoreEventSeverityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreEventSeverity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
