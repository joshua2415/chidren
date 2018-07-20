import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChidMySuffix } from 'app/shared/model/chid-my-suffix.model';

type EntityResponseType = HttpResponse<IChidMySuffix>;
type EntityArrayResponseType = HttpResponse<IChidMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class ChidMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/chids';

    constructor(private http: HttpClient) {}

    create(chid: IChidMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chid);
        return this.http
            .post<IChidMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(chid: IChidMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(chid);
        return this.http
            .put<IChidMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IChidMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IChidMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(chid: IChidMySuffix): IChidMySuffix {
        const copy: IChidMySuffix = Object.assign({}, chid, {
            createdTime: chid.createdTime != null && chid.createdTime.isValid() ? chid.createdTime.toJSON() : null,
            modifyTime: chid.modifyTime != null && chid.modifyTime.isValid() ? chid.modifyTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdTime = res.body.createdTime != null ? moment(res.body.createdTime) : null;
        res.body.modifyTime = res.body.modifyTime != null ? moment(res.body.modifyTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((chid: IChidMySuffix) => {
            chid.createdTime = chid.createdTime != null ? moment(chid.createdTime) : null;
            chid.modifyTime = chid.modifyTime != null ? moment(chid.modifyTime) : null;
        });
        return res;
    }
}
