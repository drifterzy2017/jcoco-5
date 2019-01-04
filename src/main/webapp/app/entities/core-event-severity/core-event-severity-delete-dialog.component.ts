import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICoreEventSeverity } from 'app/shared/model/core-event-severity.model';
import { CoreEventSeverityService } from './core-event-severity.service';

@Component({
    selector: 'jhi-core-event-severity-delete-dialog',
    templateUrl: './core-event-severity-delete-dialog.component.html'
})
export class CoreEventSeverityDeleteDialogComponent {
    coreEventSeverity: ICoreEventSeverity;

    constructor(
        protected coreEventSeverityService: CoreEventSeverityService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coreEventSeverityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'coreEventSeverityListModification',
                content: 'Deleted an coreEventSeverity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-core-event-severity-delete-popup',
    template: ''
})
export class CoreEventSeverityDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coreEventSeverity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CoreEventSeverityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.coreEventSeverity = coreEventSeverity;
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
