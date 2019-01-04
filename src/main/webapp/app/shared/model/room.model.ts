export interface IRoom {
    id?: number;
    roomId?: number;
    description?: string;
    floorId?: number;
    roomName?: string;
}

export class Room implements IRoom {
    constructor(
        public id?: number,
        public roomId?: number,
        public description?: string,
        public floorId?: number,
        public roomName?: string
    ) {}
}
