import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LibQuestion } from 'app/shared/model/lib-question.model';
import { LibQuestionService } from './lib-question.service';
import { LibQuestionComponent } from './lib-question.component';
import { LibQuestionDetailComponent } from './lib-question-detail.component';
import { LibQuestionUpdateComponent } from './lib-question-update.component';
import { LibQuestionDeletePopupComponent } from './lib-question-delete-dialog.component';
import { ILibQuestion } from 'app/shared/model/lib-question.model';

@Injectable({ providedIn: 'root' })
export class LibQuestionResolve implements Resolve<ILibQuestion> {
    constructor(private service: LibQuestionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LibQuestion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LibQuestion>) => response.ok),
                map((libQuestion: HttpResponse<LibQuestion>) => libQuestion.body)
            );
        }
        return of(new LibQuestion());
    }
}

export const libQuestionRoute: Routes = [
    {
        path: 'lib-question',
        component: LibQuestionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.libQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lib-question/:id/view',
        component: LibQuestionDetailComponent,
        resolve: {
            libQuestion: LibQuestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.libQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lib-question/new',
        component: LibQuestionUpdateComponent,
        resolve: {
            libQuestion: LibQuestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.libQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lib-question/:id/edit',
        component: LibQuestionUpdateComponent,
        resolve: {
            libQuestion: LibQuestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.libQuestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const libQuestionPopupRoute: Routes = [
    {
        path: 'lib-question/:id/delete',
        component: LibQuestionDeletePopupComponent,
        resolve: {
            libQuestion: LibQuestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jcoco5App.libQuestion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
