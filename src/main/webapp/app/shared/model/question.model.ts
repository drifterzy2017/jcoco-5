import { IQuiz } from 'app/shared/model//quiz.model';

export interface IQuestion {
    id?: number;
    libName?: string;
    ask?: string;
    answer?: string;
    countPass?: number;
    countFail?: number;
    countRate?: number;
    countPassAgain?: number;
    isPass?: boolean;
    quiz?: IQuiz;
}

export class Question implements IQuestion {
    constructor(
        public id?: number,
        public libName?: string,
        public ask?: string,
        public answer?: string,
        public countPass?: number,
        public countFail?: number,
        public countRate?: number,
        public countPassAgain?: number,
        public isPass?: boolean,
        public quiz?: IQuiz
    ) {
        this.isPass = this.isPass || false;
    }
}
