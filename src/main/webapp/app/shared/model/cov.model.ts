import { Moment } from 'moment';

export interface ICov {
    id?: number;
    corePointId?: number;
    coreSourceId?: number;
    engineId?: number;
    qos?: number;
    birthTime?: Moment;
    value?: string;
    state?: number;
}

export class Cov implements ICov {
    constructor(
        public id?: number,
        public corePointId?: number,
        public coreSourceId?: number,
        public engineId?: number,
        public qos?: number,
        public birthTime?: Moment,
        public value?: string,
        public state?: number
    ) {}
}
