import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Complaint } from '../models/complaint';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  // Fields

  private url = environment.baseUrl + '/api/complexes';

  // Constructor

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  // Methods

  create(complaint: Complaint, id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
      })
    };
    console.error(complaint.description);
    console.log(this.url + '/' + id + '/complaints');
    console.log(complaint.resolved);


    return this.http
      .post<Complaint>(this.url + '/' + id + '/complaints', complaint, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('ComplaintService.create(): error creating complaint');
        })
      );
  }

  getComplaintById(complexId: number, complaintId: number) {
    return this.http.get<Complaint>(this.url + '/' + complexId + '/complaints/' + complaintId)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error on ComplexService getComplaintById'
          );
        })
      );
  }

  destroy(cid: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
      })
    };
    return this.http
    .delete<boolean>(environment.baseUrl + '/api/complaints/' + cid, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Error on ComplexService Destroy'
        );
      })
    );
  }

  update(id: number, complaint: Complaint) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
      })
    };
    return this.http
    .put<Complaint>(environment.baseUrl + '/api/complaints/' + id, complaint, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'Error on ComplexService Update'
        );
      })
    );
  }
}
