import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Complex } from '../models/complex';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplexService {
  url = environment.baseUrl + '/api/complexes';

  constructor(private http: HttpClient, private authSvc: AuthService) { }

  searchApartment(apt: string) {
    return this.http.get<Complex[]>(this.url + '/' + apt)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error on ComplexService searchApartment'
          );
        })
      );
  }

  searchCity(city: string) {
    return this.http.get<Complex[]>(this.url + '/' + city)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error on ComplexService searchCity'
          );
        })
      );
  }

  getComplexById(id: number) {
    console.log(this.url);
    console.log(id);
    return this.http.get<Complex>(this.url + '/' + id + '/details')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error on ComplexService getComplexById'
          );
        })
      );
  }

  createComplex(comp) {
    console.log(comp)
    const complex = new Complex();
    complex.name = comp[0];
    complex.street = comp[0];
    complex.city = comp[1];
    complex.state = comp[2].split(' ')[0];
    complex.zip = comp[2].split(' ')[1];
    console.log(complex);
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.authSvc.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Complex>(this.url, complex, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Error on ComplexService getComplexById'
          );
        })
      );
  }
}
