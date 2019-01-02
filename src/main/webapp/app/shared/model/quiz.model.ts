import { Moment } from 'moment';
import { IQuestion } from 'app/shared/model//question.model';

export interface IQuiz {
    id?: number;
    testKey?: string;
    testName?: string;
    libName?: string;
    isAutoSubmit?: boolean;
    myRound?: number;
    startTime?: Moment;
    successTime?: Moment;
    topmostSeconds?: number;
    costSeconds?: number;
    mark?: number;
    passCount?: number;
    failCount?: number;
    centCount?: number;
    point?: number;
    used?: boolean;
    useTime?: Moment;
    useNote?: string;
    questions?: IQuestion[];
}

export class Quiz implements IQuiz {
    constructor(
        public id?: number,
        public testKey?: string,
        public testName?: string,
        public libName?: string,
        public isAutoSubmit?: boolean,
        public myRound?: number,
        public startTime?: Moment,
        public successTime?: Moment,
        public topmostSeconds?: number,
        public costSeconds?: number,
        public mark?: number,
        public passCount?: number,
        public failCount?: number,
        public centCount?: number,
        public point?: number,
        public used?: boolean,
        public useTime?: Moment,
        public useNote?: string,
        public questions?: IQuestion[]
    ) {
        this.isAutoSubmit = this.isAutoSubmit || false;
        this.used = this.used || false;
    }
}
