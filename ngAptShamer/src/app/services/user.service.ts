import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  getUser(name) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
          'Content-Type':  'application/json'
      })
    };
    return this.http.get<User>(environment.baseUrl + '/api/user/' + name, httpOptions)
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
