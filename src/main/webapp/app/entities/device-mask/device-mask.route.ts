import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeviceMask } from 'app/shared/model/device-mask.model';
import { DeviceMaskService } from './device-mask.service';
import { DeviceMaskComponent } from './device-mask.component';
import { DeviceMaskDetailComponent } from './device-mask-detail.component';
import { DeviceMaskUpdateComponent } from './device-mask-update.component';
import { DeviceMaskDeletePopupComponent } from './device-mask-delete-dialog.component';
import { IDeviceMask } from 'app/shared/model/device-mask.model';

@Injectable({ providedIn: 'root' })
export class DeviceMaskResolve implements Resolve<IDeviceMask> {
    constructor(private service: DeviceMaskService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DeviceMask> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DeviceMask>) => response.ok),
                map((deviceMask: HttpResponse<DeviceMask>) => deviceMask.body)
            );
        }
        return of(new DeviceMask());
    }
}

export const deviceMaskRoute: Routes = [
    {
        path: 'device-mask',
        component: DeviceMaskComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-mask/:id/view',
        component: DeviceMaskDetailComponent,
        resolve: {
            deviceMask: DeviceMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-mask/new',
        component: DeviceMaskUpdateComponent,
        resolve: {
            deviceMask: DeviceMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-mask/:id/edit',
        component: DeviceMaskUpdateComponent,
        resolve: {
            deviceMask: DeviceMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceMask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const deviceMaskPopupRoute: Routes = [
    {
        path: 'device-mask/:id/delete',
        component: DeviceMaskDeletePopupComponent,
        resolve: {
            deviceMask: DeviceMaskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceMask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
