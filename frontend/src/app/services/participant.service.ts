import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { Observable, catchError } from 'rxjs';
import IParticipant from '../core/interfaces/IParticipant';

@Injectable({
  providedIn: 'root',
})
export class ParticipantService {
  private baseUrl: string = 'http://localhost:8082/api';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    }),
  };

  constructor(private http: HttpClient, private configService: ConfigService) {}

  getParticipants(): Observable<IParticipant[]> {
    return this.http
      .get<IParticipant[]>(`${this.baseUrl}/participants`, this.httpOptions)
      .pipe(catchError((err) => this.configService.handleError(err)));
  }

  createParticipant(participant: IParticipant): Observable<IParticipant> {
    return this.http
      .post<IParticipant>(
        `${this.baseUrl}/participants`,
        participant,
        this.httpOptions
      )
      .pipe(catchError((err) => this.configService.handleError(err)));
  }

  updateParticipant(
    id: number,
    participant: IParticipant
  ): Observable<IParticipant> {
    return this.http
      .put<IParticipant>(
        `${this.baseUrl}/participants/${id}`,
        participant,
        this.httpOptions
      )
      .pipe(catchError((error) => this.configService.handleError(error)));
  }
}
