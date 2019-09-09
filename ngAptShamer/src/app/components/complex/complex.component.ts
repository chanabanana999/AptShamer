import { FetchCallsService } from './../../services/fetch-calls.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Complex } from 'src/app/models/complex';
import { ComplexService } from 'src/app/services/complex.service';
import { googleMapsApiKey } from '../../../assets/keys.js';


@Component({
  selector: 'app-complex',
  templateUrl: './complex.component.html',
  styleUrls: ['./complex.component.css']
})
export class ComplexComponent implements OnInit {
  complex;
  lat;
  long;
  coordinates;


  constructor(private fetch: FetchCallsService, private complexSvc: ComplexService,
              private router: Router) { }
  async ngOnInit() {
    this.coordinates = [];
    try {
    const locat = await this.fetch.location;
    let address;
    this.lat = locat.results[0].geometry.location.lat;
    this.long = locat.results[0].geometry.location.lng;
    if (locat.results.length) {
      const data = locat.results[0].formatted_address;
      address = data.split(', ');
      if (address.length === 4) {
        this.complexSvc.searchApartment(address[0]).subscribe(
          data2 => {
            this.complex = data2;
            if (data2[0]) {
              this.router.navigateByUrl(`complexes/${data2[0].id}`);
            }  else {
              this.complexSvc.createComplex(address).subscribe(
                good => {
                  console.log(good);
                  this.router.navigateByUrl(`complexes/${good.id}`);

                },
                bad => {
                  console.log(bad);
                }
              );
            }

          },
          error => {
            console.log(error);

          }
        );
      }  else {
        this.complexSvc.searchCity(address[0]).subscribe(
          data3 => {
            this.complex = data3;
            console.log('COMPEX', this.complex);
            data3.map(complex  => {
              this.getData(complex);
            });
          },
          error => {
            console.log(error);
          }
        );
      }
   } else {
      this.router.navigateByUrl('notfound');
    }
    } catch (Error) {
      console.log(Error, 'locations.component.fetch');
    }
  }

async getData(complex) {
  const response = await fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${complex.street}&key=${googleMapsApiKey}`);
  const data = await response.json();
  const coordinate = data.results[0];
  this.coordinates.push(coordinate);
}

  viewComplex(id: number) {
    this.router.navigateByUrl(`/complexes/${id}`);
  }

  onMouseOver(infoWindow, gm) {

    if (gm.lastOpen != null) {
        gm.lastOpen.close();
    }

    gm.lastOpen = infoWindow;

    infoWindow.open();
  }
}
