export interface ILibQuestion {
    id?: number;
    libName?: string;
    ask?: string;
    answer?: string;
    countPass?: number;
    countFail?: number;
    countRate?: number;
    countPassAgain?: number;
    isPass?: boolean;
}

export class LibQuestion implements ILibQuestion {
    constructor(
        public id?: number,
        public libName?: string,
        public ask?: string,
        public answer?: string,
        public countPass?: number,
        public countFail?: number,
        public countRate?: number,
        public countPassAgain?: number,
        public isPass?: boolean
    ) {
        this.isPass = this.isPass || false;
    }
}
