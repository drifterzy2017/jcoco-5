import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICoreSource } from 'app/shared/model/core-source.model';
import { CoreSourceService } from './core-source.service';

@Component({
    selector: 'jhi-core-source-delete-dialog',
    templateUrl: './core-source-delete-dialog.component.html'
})
export class CoreSourceDeleteDialogComponent {
    coreSource: ICoreSource;

    constructor(
        protected coreSourceService: CoreSourceService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coreSourceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'coreSourceListModification',
                content: 'Deleted an coreSource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-core-source-delete-popup',
    template: ''
})
export class CoreSourceDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coreSource }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CoreSourceDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.coreSource = coreSource;
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
