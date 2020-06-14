import { Component, OnInit } from '@angular/core';
import { UserProfileService } from './user-profile.service';
import { CurrentUser } from '../dto/current.user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  
  private currentUser: CurrentUser;

  constructor(private userProfileService: UserProfileService) { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.userProfileService.getUserInfo(this.currentUser.username).subscribe((rez:CurrentUser) =>{
      console.log(rez);
      this.currentUser = rez;
    })
  }

}
