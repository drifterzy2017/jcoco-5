export interface ICoreSource {
    id?: number;
    coreSourceId?: number;
    sourceName?: string;
    engineId?: number;
    mapperId?: number;
    linkState?: number;
}

export class CoreSource implements ICoreSource {
    constructor(
        public id?: number,
        public coreSourceId?: number,
        public sourceName?: string,
        public engineId?: number,
        public mapperId?: number,
        public linkState?: number
    ) {}
}
