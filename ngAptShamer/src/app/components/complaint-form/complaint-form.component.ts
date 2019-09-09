import { ComplexService } from 'src/app/services/complex.service';
import { ComplaintService } from './../../services/complaint.service';
import { Component, OnInit } from '@angular/core';
import { Complaint } from 'src/app/models/complaint';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-complaint-form',
  templateUrl: './complaint-form.component.html',
  styleUrls: ['./complaint-form.component.css']
})
export class ComplaintFormComponent implements OnInit {

  // Fields

  newComp: Complaint = new Complaint();

  // Constructor

  constructor(
              private router: Router,
              private complaintSvc: ComplaintService,
              private route: ActivatedRoute,
              private complexSvc: ComplexService
  ) { }


  // Methods

  ngOnInit() {
    console.log('Hello in oninit');

  }

  create() {
    const urlId = this.route.snapshot.paramMap.get('id');
    if (urlId) {
      this.newComp.isResolved = false;
      this.newComp.resolved = null;
      this.complaintSvc.create(this.newComp, (parseInt(urlId, 10))).subscribe (
        good => {
          this.newComp = new Complaint();
          this.router.navigateByUrl(`complexes/${(parseInt(urlId, 10))}`);
        },
        bad => {
          console.error(bad);
        }
      );
    }
  }

}
