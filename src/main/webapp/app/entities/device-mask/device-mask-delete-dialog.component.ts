import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeviceMask } from 'app/shared/model/device-mask.model';
import { DeviceMaskService } from './device-mask.service';

@Component({
    selector: 'jhi-device-mask-delete-dialog',
    templateUrl: './device-mask-delete-dialog.component.html'
})
export class DeviceMaskDeleteDialogComponent {
    deviceMask: IDeviceMask;

    constructor(
        protected deviceMaskService: DeviceMaskService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deviceMaskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'deviceMaskListModification',
                content: 'Deleted an deviceMask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-device-mask-delete-popup',
    template: ''
})
export class DeviceMaskDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceMask }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeviceMaskDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.deviceMask = deviceMask;
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
