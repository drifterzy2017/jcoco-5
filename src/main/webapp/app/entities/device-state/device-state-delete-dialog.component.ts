import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeviceState } from 'app/shared/model/device-state.model';
import { DeviceStateService } from './device-state.service';

@Component({
    selector: 'jhi-device-state-delete-dialog',
    templateUrl: './device-state-delete-dialog.component.html'
})
export class DeviceStateDeleteDialogComponent {
    deviceState: IDeviceState;

    constructor(
        protected deviceStateService: DeviceStateService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deviceStateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'deviceStateListModification',
                content: 'Deleted an deviceState'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-device-state-delete-popup',
    template: ''
})
export class DeviceStateDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deviceState }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeviceStateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.deviceState = deviceState;
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
