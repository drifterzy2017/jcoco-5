import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPointMask } from 'app/shared/model/point-mask.model';
import { PointMaskService } from './point-mask.service';

@Component({
    selector: 'jhi-point-mask-delete-dialog',
    templateUrl: './point-mask-delete-dialog.component.html'
})
export class PointMaskDeleteDialogComponent {
    pointMask: IPointMask;

    constructor(
        protected pointMaskService: PointMaskService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pointMaskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pointMaskListModification',
                content: 'Deleted an pointMask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-point-mask-delete-popup',
    template: ''
})
export class PointMaskDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pointMask }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PointMaskDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.pointMask = pointMask;
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
