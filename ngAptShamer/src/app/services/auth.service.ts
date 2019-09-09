import { UserService } from './user.service';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAdmin: boolean = false;

  constructor(private http: HttpClient) {}

  login(username, password) {
    // Make credentials

    const credentials = this.generateBasicAuthCredentials(username, password);
    let currentUser: User = new User();
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    console.log(environment.baseUrl + '/authenticate');
    // create request to authenticate credentials
    return this.http
      .get(environment.baseUrl + '/authenticate', httpOptions)
      .pipe(
        tap(res => {
          console.log(res);
          localStorage.setItem('credentials', credentials);
          this.getUser(username).subscribe(
            good => {
              console.error(good);

              currentUser = good;
            },
            bad => {
              console.error(bad);
              console.error('Something went wrong getting the user in auth service!');
            }
          );
          if (currentUser.role === 'admin') {
            this.isAdmin = true;

          }
          console.error(currentUser);

          console.error('Admin status'  + this.isAdmin);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  register(user) {
    // create request to register a new account
    return this.http.post(environment.baseUrl + '/register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  returnUserName(credentials) {
    return atob(credentials);
  }

  // hacky attempt to get user to set admin boolean
  getUser(name) {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
          'Content-Type':  'application/json'
      })
    };
    return this.http.get<User>(environment.baseUrl + '/api/user/' + name, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'AuthService.getUser(): Error Getting User'
        );
      })
    );
  }
}
