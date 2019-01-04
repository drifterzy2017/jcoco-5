export interface IPark {
    id?: number;
    parkId?: number;
    description?: string;
    latitude?: number;
    longitude?: number;
    parkAddress?: string;
    parkName?: string;
}

export class Park implements IPark {
    constructor(
        public id?: number,
        public parkId?: number,
        public description?: string,
        public latitude?: number,
        public longitude?: number,
        public parkAddress?: string,
        public parkName?: string
    ) {}
}
