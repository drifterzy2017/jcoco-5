import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CoreSource } from 'app/shared/model/core-source.model';
import { CoreSourceService } from './core-source.service';
import { CoreSourceComponent } from './core-source.component';
import { CoreSourceDetailComponent } from './core-source-detail.component';
import { CoreSourceUpdateComponent } from './core-source-update.component';
import { CoreSourceDeletePopupComponent } from './core-source-delete-dialog.component';
import { ICoreSource } from 'app/shared/model/core-source.model';

@Injectable({ providedIn: 'root' })
export class CoreSourceResolve implements Resolve<ICoreSource> {
    constructor(private service: CoreSourceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CoreSource> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CoreSource>) => response.ok),
                map((coreSource: HttpResponse<CoreSource>) => coreSource.body)
            );
        }
        return of(new CoreSource());
    }
}

export const coreSourceRoute: Routes = [
    {
        path: 'core-source',
        component: CoreSourceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-source/:id/view',
        component: CoreSourceDetailComponent,
        resolve: {
            coreSource: CoreSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-source/new',
        component: CoreSourceUpdateComponent,
        resolve: {
            coreSource: CoreSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'core-source/:id/edit',
        component: CoreSourceUpdateComponent,
        resolve: {
            coreSource: CoreSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coreSourcePopupRoute: Routes = [
    {
        path: 'core-source/:id/delete',
        component: CoreSourceDeletePopupComponent,
        resolve: {
            coreSource: CoreSourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.coreSource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
