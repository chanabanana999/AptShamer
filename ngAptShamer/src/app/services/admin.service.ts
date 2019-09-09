import { Complaint } from './../models/complaint';
import { AuthService } from 'src/app/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  url = environment.baseUrl + '/api/admin';

  constructor(private http: HttpClient, private authSvc: AuthService, private route: Router) { }

  index() {
    if (!this.authSvc.checkLogin()) {
      this.route.navigateByUrl('/home');
      }
    const httpOptions = {
      headers: new HttpHeaders({
         Authorization: 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<User[]>(this.url, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.index(): Error Retrieving List Of User'
          );
        })
      );
  }

  updateUser(id: number, user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
          'Content-Type':  'application/json'
      })
    };
    return this.http.put<User>(this.url + '/' + id, user, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.updateUser(): Error Updating User'
        );
      })
    );
  }

  getUserComplaints(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
          'Content-Type':  'application/json'
      })
    };
    return this.http.get<Complaint[]>(environment.baseUrl + '/api/complaints/user/' + id, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AdminService.getUserComplaints(): Error Getting Users Complaints'
        );
      })
    );
  }
}
