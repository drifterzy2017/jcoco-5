import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CorePointMeaning } from 'app/shared/model/core-point-meaning.model';
import { CorePointMeaningService } from './core-point-meaning.service';
import { CorePointMeaningComponent } from './core-point-meaning.component';
import { CorePointMeaningDetailComponent } from './core-point-meaning-detail.component';
import { CorePointMeaningUpdateComponent } from './core-point-meaning-update.component';
import { CorePointMeaningDeletePopupComponent } from './core-point-meaning-delete-dialog.component';
import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';

@Injectable({ providedIn: 'root' })
export class CorePointMeaningResolve implements Resolve<ICorePointMeaning> {
    constructor(private service: CorePointMeaningService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CorePointMeaning> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CorePointMeaning>) => response.ok),
                map((corePointMeaning: HttpResponse<CorePointMeaning>) => corePointMeaning.body)
            );
        }
        return of(new CorePointMeaning());
    }
}

export const corePointMeaningRoute: Routes = [
    {
        path: 'core-point-meaning',
        component: CorePointMeaningComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePointMeaning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point-meaning/:id/view',
        component: CorePointMeaningDetailComponent,
        resolve: {
            corePointMeaning: CorePointMeaningResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePointMeaning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point-meaning/new',
        component: CorePointMeaningUpdateComponent,
        resolve: {
            corePointMeaning: CorePointMeaningResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePointMeaning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-point-meaning/:id/edit',
        component: CorePointMeaningUpdateComponent,
        resolve: {
            corePointMeaning: CorePointMeaningResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePointMeaning.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const corePointMeaningPopupRoute: Routes = [
    {
        path: 'core-point-meaning/:id/delete',
        component: CorePointMeaningDeletePopupComponent,
        resolve: {
            corePointMeaning: CorePointMeaningResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.corePointMeaning.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
