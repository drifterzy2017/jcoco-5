import { Moment } from 'moment';

export interface ILiveEvent {
    id?: number;
    liveEventId?: number;
    birthTime?: Moment;
    clearedById?: number;
    clearedByName?: string;
    clearTime?: Moment;
    comment?: string;
    confirmerId?: number;
    confirmerName?: string;
    confirmTime?: Moment;
    corePointId?: number;
    corePointName?: string;
    coreSourceId?: number;
    coreSourceName?: string;
    occurRemark?: string;
    occurValue?: string;
    severityId?: number;
    severityName?: string;
    stateId?: number;
    stateName?: string;
}

export class LiveEvent implements ILiveEvent {
    constructor(
        public id?: number,
        public liveEventId?: number,
        public birthTime?: Moment,
        public clearedById?: number,
        public clearedByName?: string,
        public clearTime?: Moment,
        public comment?: string,
        public confirmerId?: number,
        public confirmerName?: string,
        public confirmTime?: Moment,
        public corePointId?: number,
        public corePointName?: string,
        public coreSourceId?: number,
        public coreSourceName?: string,
        public occurRemark?: string,
        public occurValue?: string,
        public severityId?: number,
        public severityName?: string,
        public stateId?: number,
        public stateName?: string
    ) {}
}
