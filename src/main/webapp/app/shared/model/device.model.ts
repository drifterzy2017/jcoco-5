export interface IDevice {
    id?: number;
    deviceId?: number;
    description?: string;
    deviceName?: string;
    roomId?: number;
    deviceCategory?: number;
    status?: number;
    masked?: boolean;
}

export class Device implements IDevice {
    constructor(
        public id?: number,
        public deviceId?: number,
        public description?: string,
        public deviceName?: string,
        public roomId?: number,
        public deviceCategory?: number,
        public status?: number,
        public masked?: boolean
    ) {
        this.masked = this.masked || false;
    }
}
