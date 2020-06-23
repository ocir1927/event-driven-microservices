import { Component, OnInit } from '@angular/core';
import * as $ from "jquery";
import { MatDialog } from '@angular/material/dialog';
import { DepositMoneyComponent } from 'src/app/deposit-money/deposit-money.component';
import { AccountService } from 'src/app/service/account.service';
import { CurrentUser } from 'src/app/dto/current.user';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notifications/notification.service';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/dashboard', title: 'Dashboard', icon: 'dashboard', class: '' },
  { path: '/portofolio', title: 'Portfolio', icon: 'content_paste', class: '' },
  { path: '/user-profile', title: 'User Profile', icon: 'person', class: '' },
  // { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  currentUser: CurrentUser;

  constructor(public dialog: MatDialog, private accountService: AccountService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
  }

  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  };

  openDepositDialog() {
    const depositDialog = this.dialog.open(DepositMoneyComponent, { width: '40vw', data: {} },);

    depositDialog.afterClosed().subscribe(res => {
      if (res) {
        console.log(res);
        this.accountService.depositMoney(this.currentUser.accountId, res).subscribe(
          res => { this.notificationService.showNotification("User profile was updated!", 2, "center") },
          error => { this.notificationService.showNotification("Problem updating user profile", 4, "center") });
      }
    });
  }

}
