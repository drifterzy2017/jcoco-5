import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILibQuestion } from 'app/shared/model/lib-question.model';

type EntityResponseType = HttpResponse<ILibQuestion>;
type EntityArrayResponseType = HttpResponse<ILibQuestion[]>;

@Injectable({ providedIn: 'root' })
export class LibQuestionService {
    public resourceUrl = SERVER_API_URL + 'api/lib-questions';

    constructor(protected http: HttpClient) {}

    create(libQuestion: ILibQuestion): Observable<EntityResponseType> {
        return this.http.post<ILibQuestion>(this.resourceUrl, libQuestion, { observe: 'response' });
    }

    update(libQuestion: ILibQuestion): Observable<EntityResponseType> {
        return this.http.put<ILibQuestion>(this.resourceUrl, libQuestion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILibQuestion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILibQuestion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
