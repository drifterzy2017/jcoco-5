import { Moment } from 'moment';

export interface IPointMask {
    id?: number;
    userId?: number;
    userName?: string;
    comment?: string;
    corePointId?: number;
    deviceId?: number;
    operationTime?: Moment;
}

export class PointMask implements IPointMask {
    constructor(
        public id?: number,
        public userId?: number,
        public userName?: string,
        public comment?: string,
        public corePointId?: number,
        public deviceId?: number,
        public operationTime?: Moment
    ) {}
}
