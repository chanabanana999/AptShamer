import { UserService } from './../../services/user.service';
import { User } from 'src/app/models/user';
import { UserProfile } from './../../models/user-profile';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Complaint } from 'src/app/models/complaint';
import { ComplaintService } from 'src/app/services/complaint.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  editComplaint: Complaint = null;

  complaints: Complaint = new Complaint();

  user: User = new User();

  sameUser = null;

  errorMessageText = '';

  constructor(private router: Router,
              private complaintSvc: ComplaintService,
              private route: ActivatedRoute,
              private auth: AuthService,
              private userSvc: UserService ) { }

  ngOnInit() {
    this.displayComplaint();
  }

  displayComplaint() {
    const urlId = this.route.snapshot.paramMap.get('id');
    const urlArr = this.router.url.split('/');
    const complexId = parseInt(urlArr[2], 10);
    const complaintId = parseInt(urlArr[4], 10);
    if (urlId) {
      this.complaintSvc.getComplaintById(complexId, complaintId).subscribe(
        good => {
          console.log(good);
          if (typeof good.isResolved === 'undefined') {
            good.isResolved = false;
          }
          this.complaints = good;
          this.checkUser();
        },
        bad => {
          console.log(bad);
          this.router.navigateByUrl('notfound');
        }
      );
      }
  }

  delete(cid: number) {
    const urlId = this.route.snapshot.paramMap.get('cid');
    const id = this.route.snapshot.paramMap.get('id');
    if (urlId) {
      this.complaintSvc.destroy(cid).subscribe (
        good => {
          console.log(good);
          this.router.navigateByUrl(`complexes/${parseInt(id, 10)}`);
        },
        bad => {
          console.log(bad);
          this.router.navigateByUrl('notfound');
        }
      );
      }
    }

  setEdit() {
    this.editComplaint = Object.assign({}, this.complaints);
  }

  updateComplaint(complaint: Complaint) {
    if (complaint.resolution) {
        const urlId = this.route.snapshot.paramMap.get('cid');
        const id = this.route.snapshot.paramMap.get('id');
        if (urlId) {
          this.complaintSvc.update(complaint.id, complaint).subscribe (
            good => {
              console.log(good);
              this.router.navigateByUrl(`complexes/${parseInt(id, 10)}`);
            },
            bad => {
              console.log(bad);
              this.router.navigateByUrl(`complexes/${parseInt(id, 10)}/complaints/${parseInt(urlId, 10)}`);
            }
          );
        }
    } else {
      this.errorMessageText = 'Complaint Form Must Be Filled Out';
    }
  }

  checkUser() {
   const name = this.auth.returnUserName(this.auth.getCredentials());
   console.log(name);
   const username = name.split(':');
   console.log(username[0]);
   console.log(this.complaints);
   this.userSvc.getUser(username[0]).subscribe (
    good => {
      console.log(good);
      this.user = good;
      if (this.user.profile.id === this.complaints.userProfile.id) {
       this.sameUser = true;
      }
      console.log(this.sameUser);
    },
    bad => {
      console.log(bad);
      this.sameUser = false;
      }
    );
  }
}
