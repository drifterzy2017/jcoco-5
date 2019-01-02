import { Moment } from 'moment';

export interface IBill {
    id?: number;
    name?: string;
    url?: string;
    price?: number;
    buyTime?: Moment;
}

export class Bill implements IBill {
    constructor(public id?: number, public name?: string, public url?: string, public price?: number, public buyTime?: Moment) {}
}
