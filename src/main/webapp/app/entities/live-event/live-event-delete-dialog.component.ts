import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILiveEvent } from 'app/shared/model/live-event.model';
import { LiveEventService } from './live-event.service';

@Component({
    selector: 'jhi-live-event-delete-dialog',
    templateUrl: './live-event-delete-dialog.component.html'
})
export class LiveEventDeleteDialogComponent {
    liveEvent: ILiveEvent;

    constructor(
        protected liveEventService: LiveEventService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.liveEventService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'liveEventListModification',
                content: 'Deleted an liveEvent'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-live-event-delete-popup',
    template: ''
})
export class LiveEventDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ liveEvent }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LiveEventDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.liveEvent = liveEvent;
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
