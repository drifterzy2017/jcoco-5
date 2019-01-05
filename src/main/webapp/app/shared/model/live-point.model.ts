import { Moment } from 'moment';

export interface ILivePoint {
    id?: number;
    corePointId?: number;
    corePointName?: string;
    coreSourceId?: number;
    coreSourceName?: string;
    birthTime?: Moment;
    collectValue?: string;
    state?: number;
    severity?: number;
}

export class LivePoint implements ILivePoint {
    constructor(
        public id?: number,
        public corePointId?: number,
        public corePointName?: string,
        public coreSourceId?: number,
        public coreSourceName?: string,
        public birthTime?: Moment,
        public collectValue?: string,
        public state?: number,
        public severity?: number
    ) {}
}
