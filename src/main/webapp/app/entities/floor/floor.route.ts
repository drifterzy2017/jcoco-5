import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Floor } from 'app/shared/model/floor.model';
import { FloorService } from './floor.service';
import { FloorComponent } from './floor.component';
import { FloorDetailComponent } from './floor-detail.component';
import { FloorUpdateComponent } from './floor-update.component';
import { FloorDeletePopupComponent } from './floor-delete-dialog.component';
import { IFloor } from 'app/shared/model/floor.model';

@Injectable({ providedIn: 'root' })
export class FloorResolve implements Resolve<IFloor> {
    constructor(private service: FloorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Floor> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Floor>) => response.ok),
                map((floor: HttpResponse<Floor>) => floor.body)
            );
        }
        return of(new Floor());
    }
}

export const floorRoute: Routes = [
    {
        path: 'floor',
        component: FloorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.floor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floor/:id/view',
        component: FloorDetailComponent,
        resolve: {
            floor: FloorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.floor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floor/new',
        component: FloorUpdateComponent,
        resolve: {
            floor: FloorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.floor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'floor/:id/edit',
        component: FloorUpdateComponent,
        resolve: {
            floor: FloorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.floor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const floorPopupRoute: Routes = [
    {
        path: 'floor/:id/delete',
        component: FloorDeletePopupComponent,
        resolve: {
            floor: FloorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.floor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
