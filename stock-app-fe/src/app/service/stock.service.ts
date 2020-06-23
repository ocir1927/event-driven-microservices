import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  // https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey=demo
  private searchkeyword: string = "";

  private searchUrl = `${environment.alphaVangeApi}/query?function=SYMBOL_SEARCH&apikey=${environment.alphavantageApiKey}`;
  private stockMonthlyInfoUrl = `${environment.alphaVangeApi}/query?function=TIME_SERIES_MONTHLY&apikey=${environment.alphavantageApiKey}`;
  private stockCurrentData = `${environment.alphaVangeApi}/query?function=GLOBAL_QUOTE&apikey=${environment.alphavantageApiKey}`

  
  constructor(private http: HttpClient) { }

  search(key: string): Observable<any> {
    return this.http.get(`${this.searchUrl}&keywords=${key}`).pipe(map(res => res['bestMatches']));
  }

  getMonthlyStockInfo(stockSymbol){
    return this.http.get(`${this.stockMonthlyInfoUrl}&symbol=${stockSymbol}`)
    .pipe(map(res => res['Monthly Time Series']));
  }

  getStockCurrentData(stockSymbol: string) {
    return this.http.get(`${this.stockCurrentData}&symbol=${stockSymbol}`)
    .pipe(map(res => res['Global Quote']));
  }



}
