import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private searchkeyword: string = "";

  private searchUrl = `${environment.alphaVangeApi}/query?function=SYMBOL_SEARCH&apikey=${environment.alphavantageApiKey}`

  constructor(private http: HttpClient) { }

  search(key: string): Observable<any> {
    return this.http.get(`${this.searchUrl}&keywords=${key}`).pipe(map(res => res['bestMatches']));
  }

}
