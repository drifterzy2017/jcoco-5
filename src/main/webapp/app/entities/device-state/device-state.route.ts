import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeviceState } from 'app/shared/model/device-state.model';
import { DeviceStateService } from './device-state.service';
import { DeviceStateComponent } from './device-state.component';
import { DeviceStateDetailComponent } from './device-state-detail.component';
import { DeviceStateUpdateComponent } from './device-state-update.component';
import { DeviceStateDeletePopupComponent } from './device-state-delete-dialog.component';
import { IDeviceState } from 'app/shared/model/device-state.model';

@Injectable({ providedIn: 'root' })
export class DeviceStateResolve implements Resolve<IDeviceState> {
    constructor(private service: DeviceStateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DeviceState> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DeviceState>) => response.ok),
                map((deviceState: HttpResponse<DeviceState>) => deviceState.body)
            );
        }
        return of(new DeviceState());
    }
}

export const deviceStateRoute: Routes = [
    {
        path: 'device-state',
        component: DeviceStateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-state/:id/view',
        component: DeviceStateDetailComponent,
        resolve: {
            deviceState: DeviceStateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-state/new',
        component: DeviceStateUpdateComponent,
        resolve: {
            deviceState: DeviceStateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'device-state/:id/edit',
        component: DeviceStateUpdateComponent,
        resolve: {
            deviceState: DeviceStateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceState.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const deviceStatePopupRoute: Routes = [
    {
        path: 'device-state/:id/delete',
        component: DeviceStateDeletePopupComponent,
        resolve: {
            deviceState: DeviceStateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.deviceState.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
