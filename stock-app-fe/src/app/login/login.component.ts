import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, NavigationEnd } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  error: any;

  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });


  constructor(private adminAuthService: AuthService, private router: Router) { }

  ngOnInit() {
    // if (this.adminAuthService.currentUserValue) {
    //   this.router.navigate(['/admin/dashboard']);
    // }
  }


  submit() {
    if (this.loginForm.valid) {
      console.log("form submit:", this.loginForm);
      this.adminAuthService.login(this.loginForm.value.username, this.loginForm.value.password)
        .subscribe(
          data => {
            console.log(data);
            localStorage.setItem('currentUser', JSON.stringify(data));
            this.adminAuthService.setCurrentUser(data);
            this.router.navigate(['/dashboard']);
          },
          error => {
            console.log(error);
            this.error = "Parola sau utilizatorul introdus este invalid"
          });
    }
  }

}
