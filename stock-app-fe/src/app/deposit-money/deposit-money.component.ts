import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-deposit-money',
  templateUrl: './deposit-money.component.html',
  styleUrls: ['./deposit-money.component.scss']
})
export class DepositMoneyComponent implements OnInit {

  deposit: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

}
