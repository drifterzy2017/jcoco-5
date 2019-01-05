export interface ICorePointMeaning {
    id?: number;
    coreSourceId?: number;
    corePointId?: number;
    value?: string;
    meaning?: string;
}

export class CorePointMeaning implements ICorePointMeaning {
    constructor(
        public id?: number,
        public coreSourceId?: number,
        public corePointId?: number,
        public value?: string,
        public meaning?: string
    ) {}
}
