import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventStaticByDay } from 'app/shared/model/event-static-by-day.model';
import { EventStaticByDayService } from './event-static-by-day.service';

@Component({
    selector: 'jhi-event-static-by-day-delete-dialog',
    templateUrl: './event-static-by-day-delete-dialog.component.html'
})
export class EventStaticByDayDeleteDialogComponent {
    eventStaticByDay: IEventStaticByDay;

    constructor(
        protected eventStaticByDayService: EventStaticByDayService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.eventStaticByDayService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eventStaticByDayListModification',
                content: 'Deleted an eventStaticByDay'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-event-static-by-day-delete-popup',
    template: ''
})
export class EventStaticByDayDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventStaticByDay }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EventStaticByDayDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.eventStaticByDay = eventStaticByDay;
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
