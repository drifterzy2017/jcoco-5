export interface ICoreEventSeverity {
    id?: number;
    eventSeverityId?: number;
    severityName?: string;
}

export class CoreEventSeverity implements ICoreEventSeverity {
    constructor(public id?: number, public eventSeverityId?: number, public severityName?: string) {}
}
