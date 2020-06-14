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

  private searchUrl = `${environment.alphaVangeApi}/query?function=SYMBOL_SEARCH&apikey=${environment.alphavantageApiKey}`
  private stockInfoUrl = `${environment.alphaVangeApi}/query?function=TIME_SERIES_INTRADAY&interval=5min&apikey=${environment.alphavantageApiKey}`

  constructor(private http: HttpClient) { }

  search(key: string): Observable<any> {
    return this.http.get(`${this.searchUrl}&keywords=${key}`).pipe(map(res => res['bestMatches']));
  }

  getStockInfo(stockSymbol){
    return this.http.get(`${this.stockInfoUrl}&symbol=${stockSymbol}`)
    .pipe(map(res => res['Time Series (5min)']));
  }

}
