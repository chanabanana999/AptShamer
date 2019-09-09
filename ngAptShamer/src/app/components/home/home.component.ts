import { FetchCallsService } from './../../services/fetch-calls.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SafePropertyRead } from '@angular/compiler';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  formattedAddress = '';
  errorM;
  options = {
    componentRestrictions: {
      country: ['US']
    }
  };

  images = [
    // tslint:disable-next-line: max-line-length
    // 'https://cdn.vox-cdn.com/thumbor/u2VxfDNZGeYg_mtU8zaZOHN7czE=/0x0:1921x1081/1820x1213/filters:focal(808x388:1114x694):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/54665147/Broadway_and_Magnolia_apartments_1.0.jpg',
     // tslint:disable-next-line: max-line-length
    'https://sgcweb.s3.wasabisys.com/bdcnetwork/s3fs-public/styles/content_display_image/public/Indie%20Apartments%20copy.jpg?itok=5-cykb0O',
    'https://finance-commerce.com/files/2016/09/Jefferson-at-Plymouth2.jpg',
    'https://mhpmag.com/wp-content/uploads/2018/08/6203-09-N.-Ravenswood.jpg',
    'https://milehighcre.com/wp-content/uploads/2018/09/Belle-Creek-750x375.jpg'
  ];
  // `https://picsum.photos/900/500?random&t=${Math.random()}`);

  constructor(private router: Router, private fetchSvc: FetchCallsService, private config: NgbCarouselConfig) {
    config.interval = 2000;
   }

  ngOnInit() {
  }

  async search() {

    const search = await this.fetchSvc.searchByApartment(this.formattedAddress);
    console.log(search);
    let location = null;
    if (search.results.length) {
      location = search.results[0].formatted_address;
      console.log(location);
      this.router.navigateByUrl(`/complexes`);
    } else {
      this.errorM = 'No such location exist';
    }

  }

  // @ViewChild("placesRef") placesRef : GooglePlaceDirective;

  public handleAddressChange(address: any) {
    this.formattedAddress = address.formatted_address;

}

}
