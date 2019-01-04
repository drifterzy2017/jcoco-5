export interface IFloor {
    id?: number;
    floorId?: number;
    buildingId?: number;
    description?: string;
    floorName?: string;
}

export class Floor implements IFloor {
    constructor(
        public id?: number,
        public floorId?: number,
        public buildingId?: number,
        public description?: string,
        public floorName?: string
    ) {}
}
