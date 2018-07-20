import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChidMySuffix } from 'app/shared/model/chid-my-suffix.model';

@Component({
    selector: 'jhi-chid-my-suffix-detail',
    templateUrl: './chid-my-suffix-detail.component.html'
})
export class ChidMySuffixDetailComponent implements OnInit {
    chid: IChidMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chid }) => {
            this.chid = chid;
        });
    }

    previousState() {
        window.history.back();
    }
}
