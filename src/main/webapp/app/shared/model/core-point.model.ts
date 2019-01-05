export interface ICorePoint {
    id?: number;
    corePointId?: number;
    pointName?: string;
    accuracy?: string;
    unit?: string;
    max?: string;
    min?: string;
    coreSourceId?: number;
    coreDataTypeId?: number;
    eventSeverity?: number;
    stateRuleId?: number;
    readable?: boolean;
    writable?: boolean;
    defaultValue?: string;
    step?: number;
    masked?: boolean;
}

export class CorePoint implements ICorePoint {
    constructor(
        public id?: number,
        public corePointId?: number,
        public pointName?: string,
        public accuracy?: string,
        public unit?: string,
        public max?: string,
        public min?: string,
        public coreSourceId?: number,
        public coreDataTypeId?: number,
        public eventSeverity?: number,
        public stateRuleId?: number,
        public readable?: boolean,
        public writable?: boolean,
        public defaultValue?: string,
        public step?: number,
        public masked?: boolean
    ) {
        this.readable = this.readable || false;
        this.writable = this.writable || false;
        this.masked = this.masked || false;
    }
}
