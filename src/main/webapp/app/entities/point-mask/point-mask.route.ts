import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PointMask } from 'app/shared/model/point-mask.model';
import { PointMaskService } from './point-mask.service';
import { PointMaskComponent } from './point-mask.component';
import { PointMaskDetailComponent } from './point-mask-detail.component';
import { PointMaskUpdateComponent } from './point-mask-update.component';
import { PointMaskDeletePopupComponent } from './point-mask-delete-dialog.component';
import { IPointMask } from 'app/shared/model/point-mask.model';

@Injectable({ providedIn: 'root' })
export class PointMaskResolve implements Resolve<IPointMask> {
    constructor(private service: PointMaskService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PointMask> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PointMask>) => response.ok),
                map((pointMask: HttpResponse<PointMask>) => pointMask.body)
            );
        }
        return of(new PointMask());
    }
}

export const pointMaskRoute: Routes = [
    {
        path: 'point-mask',
        component: PointMaskComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.pointMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-mask/:id/view',
        component: PointMaskDetailComponent,
        resolve: {
            pointMask: PointMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.pointMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-mask/new',
        component: PointMaskUpdateComponent,
        resolve: {
            pointMask: PointMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.pointMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-mask/:id/edit',
        component: PointMaskUpdateComponent,
        resolve: {
            pointMask: PointMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.pointMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pointMaskPopupRoute: Routes = [
    {
        path: 'point-mask/:id/delete',
        component: PointMaskDeletePopupComponent,
        resolve: {
            pointMask: PointMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.pointMask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
