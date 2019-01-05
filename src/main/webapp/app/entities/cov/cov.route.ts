import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cov } from 'app/shared/model/cov.model';
import { CovService } from './cov.service';
import { CovComponent } from './cov.component';
import { CovDetailComponent } from './cov-detail.component';
import { CovUpdateComponent } from './cov-update.component';
import { CovDeletePopupComponent } from './cov-delete-dialog.component';
import { ICov } from 'app/shared/model/cov.model';

@Injectable({ providedIn: 'root' })
export class CovResolve implements Resolve<ICov> {
    constructor(private service: CovService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cov> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cov>) => response.ok),
                map((cov: HttpResponse<Cov>) => cov.body)
            );
        }
        return of(new Cov());
    }
}

export const covRoute: Routes = [
    {
        path: 'cov',
        component: CovComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.cov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cov/:id/view',
        component: CovDetailComponent,
        resolve: {
            cov: CovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.cov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cov/new',
        component: CovUpdateComponent,
        resolve: {
            cov: CovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.cov.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cov/:id/edit',
        component: CovUpdateComponent,
        resolve: {
            cov: CovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.cov.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const covPopupRoute: Routes = [
    {
        path: 'cov/:id/delete',
        component: CovDeletePopupComponent,
        resolve: {
            cov: CovResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.cov.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
