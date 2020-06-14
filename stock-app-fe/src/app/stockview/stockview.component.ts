import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SockTradeDialogComponent } from './sock-trade-dialog/sock-trade-dialog.component';
import * as Chartist from 'chartist';
import { ActivatedRoute, ParamMap, NavigationEnd, Router, NavigationStart } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { StockService } from '../service/stock.service';
import { OrderService } from '../portofolio/order.service';
import { Order } from '../dto/order';
import { CurrentUser } from '../dto/current.user';
import { CreateOrderDto } from '../dto/create.order';

@Component({
  selector: 'app-stockview',
  templateUrl: './stockview.component.html',
  styleUrls: ['./stockview.component.scss']
})
export class StockviewComponent implements OnInit {
  stockSymbol: string;
  stockData = {};
  stockValues: any[] = [];
  currentUser: CurrentUser;

  constructor(public dialog: MatDialog,
    private route: ActivatedRoute,
    private stockService: StockService,
    private orderService: OrderService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.stockSymbol = this.route.snapshot.paramMap.get("stockSymbol");
    this.getStockData()

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // if (isPlatformBrowser(this.platformId)) {
        //   gtag('set', 'page', event.urlAfterRedirects);
        //   gtag('send', 'pageview');
        // }
        this.getStockData()
      }
    });

    this.stockData["stockSymbol"] = this.stockSymbol;

    
    console.log(this.stockSymbol);
    this.initializeChart();

  }
  getStockData() {
    this.stockSymbol = this.route.snapshot.paramMap.get("stockSymbol");
    this.stockService.getStockInfo(this.stockSymbol).subscribe(res => {
      // res => res['Time Series (5min)']
      // let queriedStockValues = res['Time Series (5min)'];
      this.stockValues = [];
      for (var i in res)
        this.stockValues.push({
          "date": i,
          ...res[i]
        });
      console.log(this.stockValues);
      // this.stockValues = res;
      this.stockData["stockPrice"] = this.stockValues[0]["1. open"];
    });


  }

  tradeStock() {
    const stockTradeDialog = this.dialog.open(SockTradeDialogComponent, { width: '40vw', data: this.stockData },);

    stockTradeDialog.afterClosed().subscribe(res => {
      if (res) {
        let units = res.invested / res.stockPrice
        units = +units.toFixed(2);
        this.orderService.createNewOrder(new CreateOrderDto(this.currentUser.accountId, res.stockSymbol, units, +res.invested)).subscribe()

      }
      //   this.userService.addUser(res).subscribe(
      //     res => {
      //       this.loadUsers();
      //     },
      //     error => this.errorHandlingService.handleError(error));
      // }
    });
  }

  initializeChart() {
    const dataDailySalesChart: any = {
      labels: ['J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D'],
      series: [
        [323, 273, 225, 287, 318, 338]

      ]
    };

    const optionsDailySalesChart: any = {
      lineSmooth: Chartist.Interpolation.cardinal({
        tension: 0
      }),
      low: Math.min(...dataDailySalesChart.series[0]) - 0.1 * Math.min(...dataDailySalesChart.series[0]),
      high: Math.max(...dataDailySalesChart.series[0]) + 0.1 * Math.max(...dataDailySalesChart.series[0]), // we recommend you to set the high sa the biggest value + something for a better look
      chartPadding: { top: 0, right: 0, bottom: 0, left: 0 },
    }

    var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

    this.startAnimationForLineChart(dailySalesChart);
  }

  startAnimationForLineChart(chart) {
    let seq: any, delays: any, durations: any;
    seq = 0;
    delays = 80;
    durations = 500;

    chart.on('draw', function (data) {
      if (data.type === 'line' || data.type === 'area') {
        data.element.animate({
          d: {
            begin: 600,
            dur: 700,
            from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
            to: data.path.clone().stringify(),
            easing: Chartist.Svg.Easing.easeOutQuint
          }
        });
      } else if (data.type === 'point') {
        seq++;
        data.element.animate({
          opacity: {
            begin: seq * delays,
            dur: durations,
            from: 0,
            to: 1,
            easing: 'ease'
          }
        });
      }
    });
    seq = 0;
  };


}
