export interface IBuilding {
    id?: number;
    buildingId?: number;
    buildingName?: string;
    description?: string;
    parkId?: number;
}

export class Building implements IBuilding {
    constructor(
        public id?: number,
        public buildingId?: number,
        public buildingName?: string,
        public description?: string,
        public parkId?: number
    ) {}
}
