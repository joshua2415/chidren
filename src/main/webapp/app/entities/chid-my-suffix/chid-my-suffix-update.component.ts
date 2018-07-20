import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IChidMySuffix } from 'app/shared/model/chid-my-suffix.model';
import { ChidMySuffixService } from './chid-my-suffix.service';

@Component({
    selector: 'jhi-chid-my-suffix-update',
    templateUrl: './chid-my-suffix-update.component.html'
})
export class ChidMySuffixUpdateComponent implements OnInit {
    private _chid: IChidMySuffix;
    isSaving: boolean;
    createdTime: string;
    modifyTime: string;

    constructor(private chidService: ChidMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ chid }) => {
            this.chid = chid;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.chid.createdTime = moment(this.createdTime, DATE_TIME_FORMAT);
        this.chid.modifyTime = moment(this.modifyTime, DATE_TIME_FORMAT);
        if (this.chid.id !== undefined) {
            this.subscribeToSaveResponse(this.chidService.update(this.chid));
        } else {
            this.subscribeToSaveResponse(this.chidService.create(this.chid));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChidMySuffix>>) {
        result.subscribe((res: HttpResponse<IChidMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get chid() {
        return this._chid;
    }

    set chid(chid: IChidMySuffix) {
        this._chid = chid;
        this.createdTime = moment(chid.createdTime).format(DATE_TIME_FORMAT);
        this.modifyTime = moment(chid.modifyTime).format(DATE_TIME_FORMAT);
    }
}
