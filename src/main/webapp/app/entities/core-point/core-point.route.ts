import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CorePoint } from 'app/shared/model/core-point.model';
import { CorePointService } from './core-point.service';
import { CorePointComponent } from './core-point.component';
import { CorePointDetailComponent } from './core-point-detail.component';
import { CorePointUpdateComponent } from './core-point-update.component';
import { CorePointDeletePopupComponent } from './core-point-delete-dialog.component';
import { ICorePoint } from 'app/shared/model/core-point.model';

@Injectable({ providedIn: 'root' })
export class CorePointResolve implements Resolve<ICorePoint> {
    constructor(private service: CorePointService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CorePoint> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CorePoint>) => response.ok),
                map((corePoint: HttpResponse<CorePoint>) => corePoint.body)
            );
        }
        return of(new CorePoint());
    }
}

export const corePointRoute: Routes = [
    {
        path: 'core-point',
        component: CorePointComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point/:id/view',
        component: CorePointDetailComponent,
        resolve: {
            corePoint: CorePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point/new',
        component: CorePointUpdateComponent,
        resolve: {
            corePoint: CorePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point/:id/edit',
        component: CorePointUpdateComponent,
        resolve: {
            corePoint: CorePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePoint.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const corePointPopupRoute: Routes = [
    {
        path: 'core-point/:id/delete',
        component: CorePointDeletePopupComponent,
        resolve: {
            corePoint: CorePointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePoint.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
