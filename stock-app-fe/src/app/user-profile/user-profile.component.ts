import { Component, OnInit } from '@angular/core';
import { UserProfileService } from './user-profile.service';
import { CurrentUser } from '../dto/current.user';
import { NotificationService } from '../notifications/notification.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  
  private currentUser: CurrentUser;

  constructor(private userProfileService: UserProfileService, private notificationService: NotificationService) { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.userProfileService.getUserInfo(this.currentUser.username).subscribe((rez:CurrentUser) =>{
      console.log(rez);
      this.currentUser = rez;
    })
  }

  updateProfile(){
    this.userProfileService.updateProfile(this.currentUser).subscribe(
      res=> {this.notificationService.showNotification("User profile was updated!",2,"center")},
      error => {this.notificationService.showNotification("Problem updating user profile",4,"center")}
    );
  }

}
