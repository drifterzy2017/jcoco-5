export interface IDeviceState {
    id?: number;
    stateId?: number;
    stateName?: string;
    remark?: string;
}

export class DeviceState implements IDeviceState {
    constructor(public id?: number, public stateId?: number, public stateName?: string, public remark?: string) {}
}
