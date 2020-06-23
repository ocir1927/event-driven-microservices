import { Component, OnInit } from '@angular/core';
import { WithdrawMoney } from '../dto/withdraw';
import { CurrentUser } from '../dto/current.user';
import { AccountService } from '../service/account.service';

@Component({
  selector: 'app-withdraw-money',
  templateUrl: './withdraw-money.component.html',
  styleUrls: ['./withdraw-money.component.scss']
})
export class WithdrawMoneyComponent implements OnInit {

  withdraw: WithdrawMoney = new WithdrawMoney();
  currentUser: CurrentUser;

  constructor() { }

  ngOnInit(): void {
    // this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    // this.withdraw.accountId = this.currentUser.accountId;
  }



}
