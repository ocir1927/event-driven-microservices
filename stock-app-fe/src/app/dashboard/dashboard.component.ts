import { Component, OnInit, OnDestroy } from '@angular/core';
import * as Chartist from 'chartist';
import { AccountService } from '../service/account.service';
import { CurrentUser } from '../dto/current.user';
import { MatDialog } from '@angular/material/dialog';
import { WithdrawMoneyComponent } from '../withdraw-money/withdraw-money.component';
import { NotificationService } from '../notifications/notification.service';
import { OrderService } from '../portofolio/order.service';
import { WebSocketAPI } from '../portofolio/websocket.api';
import { StockUpdate } from '../dto/stock.update';
import { StockProjection } from '../dto/stock.projection';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {
  accountBalance: number;
  currentUser: CurrentUser;
  orderCount: number;
  webSocketAPI: WebSocketAPI;
  stockUpdates: StockUpdate[];
  watchlist;
  orderProjections: StockProjection[] = [];

  constructor(private accountService: AccountService, public dialog: MatDialog,
    private notificationService: NotificationService, private orderService: OrderService) {
    this.webSocketAPI = new WebSocketAPI(this);
  }

  withdrawMoney() {
    const withdrawDialog = this.dialog.open(WithdrawMoneyComponent, { width: '40vw' });

    withdrawDialog.afterClosed().subscribe(res => {
      if (res) {
        // console.log("withdraw item: ", res)
        res["accountId"] = this.currentUser.accountId;
        this.accountService.withdrawMoney(this.currentUser.accountId, res).subscribe(res => {
          this.notificationService.showNotification("Withdraw Successfull", 2, "center")
        })
      }
    });
  }

  ngOnInit() {
    this.connect();

    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.watchlist = JSON.parse(localStorage.getItem(`${this.currentUser.username}_watchlist`));


    this.accountService.getAccountBallance(this.currentUser.accountId).subscribe(res => {
      this.accountBalance = res['accountBalance'];
    });

    this.orderService.getOrderCount(this.currentUser.accountId).subscribe(
      (res: number) => {
        this.orderCount = res;
      }
    );




    this.initializeChart();
  }


  updateWatchList() {
    this.orderProjections = [];
    if (this.watchlist)
    console.log(this.watchlist);
    
      this.watchlist.forEach(ticker => {
        let foundCorelation = this.stockUpdates.find(stkUpdt => ticker.toUpperCase() === stkUpdt.ticker.toUpperCase());
        if (foundCorelation) {
          let stockProjection: StockProjection = new StockProjection();
          stockProjection.stockSymbol = ticker;
          stockProjection.currentValue = foundCorelation.last;
          this.orderProjections.push(stockProjection);
        }
      })
  }


  initializeChart() {
    var datawebsiteViewsChart = {
      labels: ['J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D'],
      series: [
        [500, 800, 1250, 2000, 1700, 2500]

      ]
    };
    var optionswebsiteViewsChart = {
      axisX: {
        showGrid: false
      },
      low: Math.min(...datawebsiteViewsChart.series[0]) - 0.1 * Math.min(...datawebsiteViewsChart.series[0]),
      high: Math.max(...datawebsiteViewsChart.series[0]) + 0.1 * Math.max(...datawebsiteViewsChart.series[0]),
      chartPadding: { top: 0, right: 5, bottom: 0, left: 0 }
    };
    var responsiveOptions: any[] = [
      ['screen and (max-width: 640px)', {
        seriesBarDistance: 5,
        axisX: {
          labelInterpolationFnc: function (value) {
            return value[0];
          }
        }
      }]
    ];
    var websiteViewsChart = new Chartist.Bar('#websiteViewsChart', datawebsiteViewsChart, optionswebsiteViewsChart, responsiveOptions);

    //start animation for the Emails Subscription Chart
    this.startAnimationForBarChart(websiteViewsChart);
  }


  startAnimationForBarChart(chart) {
    let seq2: any, delays2: any, durations2: any;

    seq2 = 0;
    delays2 = 80;
    durations2 = 500;
    chart.on('draw', function (data) {
      if (data.type === 'bar') {
        seq2++;
        data.element.animate({
          opacity: {
            begin: seq2 * delays2,
            dur: durations2,
            from: 0,
            to: 1,
            easing: 'ease'
          }
        });
      }
    });

    seq2 = 0;
  };


  ngOnDestroy(): void {
    this.disconnect();
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage(message) {
    this.webSocketAPI._send(message);
  }


  handleMessage(message: StockUpdate[]) {
    this.stockUpdates = message;
    this.updateWatchList();
    // this.manageOrders();
  }


}
