import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SockTradeDialogComponent } from './sock-trade-dialog/sock-trade-dialog.component';
import * as Chartist from 'chartist';

@Component({
  selector: 'app-stockview',
  templateUrl: './stockview.component.html',
  styleUrls: ['./stockview.component.scss']
})
export class StockviewComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {


    const dataDailySalesChart: any = {
      labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
      series: [
        [12, 17, 7, 17, 23, 18, 38]
      ]
    };

    const optionsDailySalesChart: any = {
      lineSmooth: Chartist.Interpolation.cardinal({
        tension: 0
      }),
      low: 0,
      high: 50, // we recommend you to set the high sa the biggest value + something for a better look
      chartPadding: { top: 0, right: 0, bottom: 0, left: 0 },
    }

    var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

    this.startAnimationForLineChart(dailySalesChart);
  }

  tradeStock() {
    const stockTradeDialog = this.dialog.open(SockTradeDialogComponent, {
    });

    stockTradeDialog.afterClosed().subscribe(res => {
      if (res) {
        console.log("adding user: ", res);
      }
      //   this.userService.addUser(res).subscribe(
      //     res => {
      //       this.loadUsers();
      //     },
      //     error => this.errorHandlingService.handleError(error));
      // }
    });
  }


  startAnimationForLineChart(chart){
    let seq: any, delays: any, durations: any;
    seq = 0;
    delays = 80;
    durations = 500;

    chart.on('draw', function(data) {
      if(data.type === 'line' || data.type === 'area') {
        data.element.animate({
          d: {
            begin: 600,
            dur: 700,
            from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
            to: data.path.clone().stringify(),
            easing: Chartist.Svg.Easing.easeOutQuint
          }
        });
      } else if(data.type === 'point') {
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
