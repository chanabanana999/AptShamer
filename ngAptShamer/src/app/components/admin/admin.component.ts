import { NavigationComponent } from './../navigation/navigation.component';
import { AdminService } from './../../services/admin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Complaint } from 'src/app/models/complaint';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  user: User = new User();

  userComplaints: Complaint[] = null;

  constructor(private adminSvc: AdminService, private nav: NavigationComponent) { }

  ngOnInit() {
    this.loadUsers();
    this.nav.setUser();
  }

  loadUsers() {
    this.adminSvc.index().subscribe(
      good => {
        console.log(good);
        this.users = good;
      },
      bad => {
        console.log(bad);
      }
    );
  }

  updateUser(user: User) {
    this.adminSvc.updateUser(user.id, user).subscribe(
      good => {
        console.log(good);
        this.ngOnInit();
      },
      bad => {
        console.error(bad);
      }
    );
  }

  makeUserEnabled(user: User) {
    user.enabled = true;
    this.updateUser(user);
  }


  makeUserDisabled(user: User) {
  user.enabled = false;
  this.updateUser(user);
  }

  showAllUserComplaints(oneUser: User) {
    console.log(oneUser.profile.id);
    this.user = oneUser;
    this.adminSvc.getUserComplaints(oneUser.profile.id).subscribe(
      good => {
        console.log(good);
        this.userComplaints = good;
      },
      bad => {
        console.error(bad);
      }
    );
  }

}
