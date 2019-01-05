import { Moment } from 'moment';

export interface IDesiredCov {
    id?: number;
    corePointId?: number;
    coreSourceId?: number;
    engineId?: number;
    desiredValue?: string;
    userId?: number;
    birthTime?: Moment;
    state?: number;
    message?: string;
}

export class DesiredCov implements IDesiredCov {
    constructor(
        public id?: number,
        public corePointId?: number,
        public coreSourceId?: number,
        public engineId?: number,
        public desiredValue?: string,
        public userId?: number,
        public birthTime?: Moment,
        public state?: number,
        public message?: string
    ) {}
}
