import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICorePointMeaning } from 'app/shared/model/core-point-meaning.model';
import { CorePointMeaningService } from './core-point-meaning.service';

@Component({
    selector: 'jhi-core-point-meaning-delete-dialog',
    templateUrl: './core-point-meaning-delete-dialog.component.html'
})
export class CorePointMeaningDeleteDialogComponent {
    corePointMeaning: ICorePointMeaning;

    constructor(
        protected corePointMeaningService: CorePointMeaningService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.corePointMeaningService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'corePointMeaningListModification',
                content: 'Deleted an corePointMeaning'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-core-point-meaning-delete-popup',
    template: ''
})
export class CorePointMeaningDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ corePointMeaning }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CorePointMeaningDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.corePointMeaning = corePointMeaning;
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
