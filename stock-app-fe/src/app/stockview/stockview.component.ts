import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SockTradeDialogComponent } from './sock-trade-dialog/sock-trade-dialog.component';
import * as Chartist from 'chartist';
import { ActivatedRoute, ParamMap, NavigationEnd, Router, NavigationStart, ResolveEnd } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { StockService } from '../service/stock.service';
import { OrderService } from '../portofolio/order.service';
import { Order } from '../dto/order';
import { CurrentUser } from '../dto/current.user';
import { CreateOrderDto } from '../dto/create.order';
import { Observable, Subscription } from 'rxjs';
import { NotificationService } from '../notifications/notification.service';
import { element } from 'protractor';

@Component({
  selector: 'app-stockview',
  templateUrl: './stockview.component.html',
  styleUrls: ['./stockview.component.scss']
})
export class StockviewComponent implements OnInit, OnDestroy {
  stockSymbol: string;
  stockData = {};
  stockValues: any[] = [];
  currentUser: CurrentUser;
  routerSubscription: Subscription;

  constructor(public dialog: MatDialog,
    private route: ActivatedRoute,
    private stockService: StockService,
    private orderService: OrderService,
    private router: Router,
    private notificationService: NotificationService
  ) { }


  ngOnDestroy(): void {
    console.log("On destroy called, unsubscribing router events");

    this.routerSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.stockSymbol = this.route.snapshot.paramMap.get("stockSymbol");
    this.getStockData()

    this.routerSubscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // if (isPlatformBrowser(this.platformId)) {
        //   gtag('set', 'page', event.urlAfterRedirects);
        //   gtag('send', 'pageview');
        // }
        this.getStockData()
      }
    });

    this.stockData["stockSymbol"] = this.stockSymbol;
  }

  addToWatchList(stockSymbol: string) {
    let watchList = JSON.parse(localStorage.getItem(`${this.currentUser.username}_watchlist`));

    if (watchList == null || watchList === {} || watchList === [] || watchList === "") {
      watchList = [];
      watchList.push(stockSymbol);
    } else {
      if (!watchList.includes(stockSymbol))
        watchList.push(stockSymbol);
    }
    console.log(watchList);

    localStorage.setItem(`${this.currentUser.username}_watchlist`, JSON.stringify(watchList));
  }

  getStockData() {
    this.stockSymbol = this.route.snapshot.paramMap.get("stockSymbol");
    this.stockService.getStockCurrentData(this.stockSymbol).subscribe(res => {
      console.log(res);
      this.stockData["stockPrice"] = res["05. price"];
    });

    this.stockService.getMonthlyStockInfo(this.stockSymbol).subscribe(res => {

      this.stockValues = [];


      for (var i in res)
        this.stockValues.push({
          "date": i,
          ...res[i]
        });

      this.stockValues = this.stockValues.slice(0, 12).reverse();
      console.log("sliced months: ", this.stockValues);
      this.initializeChart();
    });


  }

  tradeStock() {
    const stockTradeDialog = this.dialog.open(SockTradeDialogComponent, { width: '40vw', data: this.stockData },);

    stockTradeDialog.afterClosed().subscribe(res => {
      if (res) {
        let units = res.invested / res.stockPrice
        units = +units.toFixed(2);
        this.orderService.createNewOrder(new CreateOrderDto(this.currentUser.accountId, res.stockSymbol, units, +res.invested)).subscribe(
          res => { this.notificationService.showNotification("Your order was placed", 2, "center") },
          error => { this.notificationService.showNotification("There was a problem creating your order", 4, "center") }
        );
      }
    });
  }

  initializeChart() {
    let monthlyValues = [];
    this.stockValues.forEach(element => {
      monthlyValues.push(element["4. close"])
    });
    const dataDailySalesChart: any = {
      labels: ['Aug 19\'', 'Sept 19\'', 'Oct 19\'', 'Nov 19\'', 'Dec 19\'', 'Jan 20\'', 'Feb 20\'', 'Mar 20\'', 'Apr 20\'', 'May 20\'', 'Jun 20\'', 'Jul 20\'',],
      series: [
        monthlyValues
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
