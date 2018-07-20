import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChidMySuffix } from 'app/shared/model/chid-my-suffix.model';
import { ChidMySuffixService } from './chid-my-suffix.service';

@Component({
    selector: 'jhi-chid-my-suffix-delete-dialog',
    templateUrl: './chid-my-suffix-delete-dialog.component.html'
})
export class ChidMySuffixDeleteDialogComponent {
    chid: IChidMySuffix;

    constructor(private chidService: ChidMySuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chidService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'chidListModification',
                content: 'Deleted an chid'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-chid-my-suffix-delete-popup',
    template: ''
})
export class ChidMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chid }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChidMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.chid = chid;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
