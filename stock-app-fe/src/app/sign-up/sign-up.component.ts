import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignUpDto } from './signup.dto';
import { AuthService } from '../login/auth.service';
import { Router } from '@angular/router';
import { NotificationService } from '../notifications/notification.service';
import { map, tap } from 'rxjs/operators';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  error: any;

  constructor(private adminAuthService: AuthService,
    private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit(): void {
  }

  submit() {
    // this.notificationService.showNotification("sper ca merg notificarile",2,"center")
    if (this.signUpForm.valid) {
      let signUpDto = new SignUpDto(this.signUpForm.value.username, this.signUpForm.value.password, this.signUpForm.value.name, this.signUpForm.value.email);
      console.log("form submit:", this.signUpForm);
      this.adminAuthService.signUp(signUpDto)
        .subscribe(
          data => {
            console.log(data);
            this.notificationService.showNotification("Sign-Up complete, now redirecting to login page...", 2, "center")
            setTimeout(() => this.router.navigate(['/login']), 1500);
          },
          error => {
            console.log(error);
            this.error = "Parola sau utilizatorul introdus este invalid"
          });
    }
  }

}
