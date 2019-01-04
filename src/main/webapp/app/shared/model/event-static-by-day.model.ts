export interface IEventStaticByDay {
    id?: number;
    staticDay?: number;
    severity1?: number;
    severity2?: number;
    severity3?: number;
    severity4?: number;
}

export class EventStaticByDay implements IEventStaticByDay {
    constructor(
        public id?: number,
        public staticDay?: number,
        public severity1?: number,
        public severity2?: number,
        public severity3?: number,
        public severity4?: number
    ) {}
}
