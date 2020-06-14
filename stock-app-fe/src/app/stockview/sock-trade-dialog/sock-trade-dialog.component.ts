import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-sock-trade-dialog',
  templateUrl: './sock-trade-dialog.component.html',
  styleUrls: ['./sock-trade-dialog.component.scss']
})
export class SockTradeDialogComponent implements OnInit {


  tradeInfo = {
    "stockSymbol" : "",
    "stockPrice": 0,
    "units" : 0,
    "invested": 0
  }


  constructor(@Inject(MAT_DIALOG_DATA) public data) {
    console.log(data);
    this.tradeInfo.stockSymbol = data["stockSymbol"];
    this.tradeInfo.stockPrice = data["stockPrice"];
    console.log(this.tradeInfo);
    
   }

  ngOnInit(): void {
  }

}
