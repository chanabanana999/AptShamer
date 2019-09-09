import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  regForm: User = new User();

  constructor(private authSrv: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register(regForm) {
    console.log(regForm);
    this.authSrv.register(regForm).subscribe(
      next => {
        console.log(next);
        this.authSrv.login(regForm.username, regForm.password).subscribe(
          good => {
            console.log(good);
            this.router.navigateByUrl('/home');
          },
          err => {
            console.log(err);
            this.router.navigateByUrl('/');
          }
        );
      },
      error => {
        console.log(error);
        this.router.navigateByUrl('/');
      }
    );
  }

}
