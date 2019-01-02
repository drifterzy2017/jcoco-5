import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILibQuestion } from 'app/shared/model/lib-question.model';
import { LibQuestionService } from './lib-question.service';

@Component({
    selector: 'jhi-lib-question-delete-dialog',
    templateUrl: './lib-question-delete-dialog.component.html'
})
export class LibQuestionDeleteDialogComponent {
    libQuestion: ILibQuestion;

    constructor(
        protected libQuestionService: LibQuestionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.libQuestionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'libQuestionListModification',
                content: 'Deleted an libQuestion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-lib-question-delete-popup',
    template: ''
})
export class LibQuestionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ libQuestion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LibQuestionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.libQuestion = libQuestion;
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
