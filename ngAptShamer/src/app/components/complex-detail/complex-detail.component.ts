import { AuthService } from 'src/app/services/auth.service';
import { HttpClient } from 'selenium-webdriver/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ComplexService } from 'src/app/services/complex.service';
import { Complex } from 'src/app/models/complex';
import { FetchCallsService } from 'src/app/services/fetch-calls.service';

@Component({
  selector: 'app-complex-detail',
  templateUrl: './complex-detail.component.html',
  styleUrls: ['./complex-detail.component.css']
})
export class ComplexDetailComponent implements OnInit {

  lat;
  long;
complex: Complex = new Complex();

  constructor(private router: Router,
              private fetch: FetchCallsService,
              private complexSvc: ComplexService,
              private route: ActivatedRoute,
              private auth: AuthService ) { }

  ngOnInit() {
    this.displayComplex();
  }

  async displayComplex() {
    const locat = await this.fetch.location;
    console.log(locat);
    this.lat = locat.results[0].geometry.location.lat;
    this.long = locat.results[0].geometry.location.lng;
    const urlId = this.route.snapshot.paramMap.get('id');
    if (urlId) {
      this.complexSvc.getComplexById(parseInt(urlId, 10)).subscribe(
        good => {
          console.log(good);
          this.complex = good;
        },
        bad => {
          console.log(bad);
          this.router.navigateByUrl('notfound');
        }
      );
    }
  }

  viewComplaint(id: number) {
    this.router.navigateByUrl(`/complexes/${this.complex.id}/complaints/${id}`);
  }

  createNew() {
    this.router.navigateByUrl(`complexes/${this.complex.id}/complaints`);
  }

  loggedIn = function() {
    return this.auth.checkLogin();
  }

}
