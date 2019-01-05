import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DesiredCov } from 'app/shared/model/desired-cov.model';
import { DesiredCovService } from './desired-cov.service';
import { DesiredCovComponent } from './desired-cov.component';
import { DesiredCovDetailComponent } from './desired-cov-detail.component';
import { DesiredCovUpdateComponent } from './desired-cov-update.component';
import { DesiredCovDeletePopupComponent } from './desired-cov-delete-dialog.component';
import { IDesiredCov } from 'app/shared/model/desired-cov.model';

@Injectable({ providedIn: 'root' })
export class DesiredCovResolve implements Resolve<IDesiredCov> {
    constructor(private service: DesiredCovService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DesiredCov> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DesiredCov>) => response.ok),
                map((desiredCov: HttpResponse<DesiredCov>) => desiredCov.body)
            );
        }
        return of(new DesiredCov());
    }
}

export const desiredCovRoute: Routes = [
    {
        path: 'desired-cov',
        component: DesiredCovComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.desiredCov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'desired-cov/:id/view',
        component: DesiredCovDetailComponent,
        resolve: {
            desiredCov: DesiredCovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.desiredCov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'desired-cov/new',
        component: DesiredCovUpdateComponent,
        resolve: {
            desiredCov: DesiredCovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.desiredCov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'desired-cov/:id/edit',
        component: DesiredCovUpdateComponent,
        resolve: {
            desiredCov: DesiredCovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.desiredCov.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const desiredCovPopupRoute: Routes = [
    {
        path: 'desired-cov/:id/delete',
        component: DesiredCovDeletePopupComponent,
        resolve: {
            desiredCov: DesiredCovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.desiredCov.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
