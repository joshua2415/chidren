import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChidrenSharedModule } from 'app/shared';
import {
    ChidMySuffixComponent,
    ChidMySuffixDetailComponent,
    ChidMySuffixUpdateComponent,
    ChidMySuffixDeletePopupComponent,
    ChidMySuffixDeleteDialogComponent,
    chidRoute,
    chidPopupRoute
} from './';

const ENTITY_STATES = [...chidRoute, ...chidPopupRoute];

@NgModule({
    imports: [ChidrenSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChidMySuffixComponent,
        ChidMySuffixDetailComponent,
        ChidMySuffixUpdateComponent,
        ChidMySuffixDeleteDialogComponent,
        ChidMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ChidMySuffixComponent,
        ChidMySuffixUpdateComponent,
        ChidMySuffixDeleteDialogComponent,
        ChidMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChidrenChidMySuffixModule {}
