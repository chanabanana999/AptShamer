import { UserService } from './../../services/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { LoginComponent } from '../login/login.component';
import { isUndefined } from 'util';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  admin: User = new User();

  user: User = new User();

  userIsAdmin = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private userSvc: UserService
  ) {}

  ngOnInit() {
    this.setUser();
    this.userIsAdmin = this.checkAdmin();
    // location.reload();
    // this.userIsAdmin = false;
  }

  loggedIn = function() {
    return this.auth.checkLogin();
  };

  logout = function() {
    this.userIsAdmin = false;
    return this.auth.logout();
  };

  setUser() {
    if (this.auth.checkLogin()) {
      const user = this.auth.getCredentials();
      console.log(user);
      const username = this.auth.returnUserName(user).split(':');
      console.log(username[0]);
      this.userSvc.getUser(username[0]).subscribe(
        good => {
          // console.log(good);
          // this.user = good;
          // this.userIsAdmin = good.role === 'admin';
          good.role === 'admin' ? this.userIsAdmin = true : this.userIsAdmin = false;
          console.log(this.userIsAdmin);
        },
        bad => {
          console.log(bad);
          return false;
        }
        );
      }
  }

  checkAdmin() {
    return this.userIsAdmin;
  }

}
