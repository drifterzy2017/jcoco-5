import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDesiredCov } from 'app/shared/model/desired-cov.model';
import { DesiredCovService } from './desired-cov.service';

@Component({
    selector: 'jhi-desired-cov-delete-dialog',
    templateUrl: './desired-cov-delete-dialog.component.html'
})
export class DesiredCovDeleteDialogComponent {
    desiredCov: IDesiredCov;

    constructor(
        protected desiredCovService: DesiredCovService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.desiredCovService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'desiredCovListModification',
                content: 'Deleted an desiredCov'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-desired-cov-delete-popup',
    template: ''
})
export class DesiredCovDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ desiredCov }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DesiredCovDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.desiredCov = desiredCov;
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
