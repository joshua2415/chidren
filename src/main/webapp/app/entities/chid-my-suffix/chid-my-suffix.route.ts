import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ChidMySuffix } from 'app/shared/model/chid-my-suffix.model';
import { ChidMySuffixService } from './chid-my-suffix.service';
import { ChidMySuffixComponent } from './chid-my-suffix.component';
import { ChidMySuffixDetailComponent } from './chid-my-suffix-detail.component';
import { ChidMySuffixUpdateComponent } from './chid-my-suffix-update.component';
import { ChidMySuffixDeletePopupComponent } from './chid-my-suffix-delete-dialog.component';
import { IChidMySuffix } from 'app/shared/model/chid-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class ChidMySuffixResolve implements Resolve<IChidMySuffix> {
    constructor(private service: ChidMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((chid: HttpResponse<ChidMySuffix>) => chid.body));
        }
        return of(new ChidMySuffix());
    }
}

export const chidRoute: Routes = [
    {
        path: 'chid-my-suffix',
        component: ChidMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'chidrenApp.chid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chid-my-suffix/:id/view',
        component: ChidMySuffixDetailComponent,
        resolve: {
            chid: ChidMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'chidrenApp.chid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chid-my-suffix/new',
        component: ChidMySuffixUpdateComponent,
        resolve: {
            chid: ChidMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'chidrenApp.chid.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chid-my-suffix/:id/edit',
        component: ChidMySuffixUpdateComponent,
        resolve: {
            chid: ChidMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'chidrenApp.chid.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chidPopupRoute: Routes = [
    {
        path: 'chid-my-suffix/:id/delete',
        component: ChidMySuffixDeletePopupComponent,
        resolve: {
            chid: ChidMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'chidrenApp.chid.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
