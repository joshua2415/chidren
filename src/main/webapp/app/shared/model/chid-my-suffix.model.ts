import { Moment } from 'moment';

export const enum Sex {
    MALE = 'MALE',
    FEMALE = 'FEMALE'
}

export const enum Currency {
    USD = 'USD',
    KRW = 'KRW'
}

export interface IChidMySuffix {
    id?: number;
    fullName?: string;
    sex?: Sex;
    birthYear?: number;
    entranceYear?: number;
    introduction?: string;
    photoLink?: string;
    etc?: string;
    supportedFund?: number;
    currency?: Currency;
    createdTime?: Moment;
    modifyTime?: Moment;
}

export class ChidMySuffix implements IChidMySuffix {
    constructor(
        public id?: number,
        public fullName?: string,
        public sex?: Sex,
        public birthYear?: number,
        public entranceYear?: number,
        public introduction?: string,
        public photoLink?: string,
        public etc?: string,
        public supportedFund?: number,
        public currency?: Currency,
        public createdTime?: Moment,
        public modifyTime?: Moment
    ) {}
}
