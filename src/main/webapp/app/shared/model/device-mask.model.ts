import { Moment } from 'moment';

export interface IDeviceMask {
    id?: number;
    userId?: number;
    userName?: string;
    comment?: string;
    deviceId?: number;
    operationTime?: Moment;
}

export class DeviceMask implements IDeviceMask {
    constructor(
        public id?: number,
        public userId?: number,
        public userName?: string,
        public comment?: string,
        public deviceId?: number,
        public operationTime?: Moment
    ) {}
}
