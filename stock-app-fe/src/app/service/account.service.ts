import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { WithdrawMoney } from '../dto/withdraw';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private userApiUrl = `${environment.apiUrl}/api/bank-accounts`

  constructor(private http: HttpClient) { }

  getAccountBallance(accountId) {
    return this.http.get(`${this.userApiUrl}/accounts/${accountId}`)
  }

  depositMoney(accountId, ammount) {

    let httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
    };

    return this.http.put(`${this.userApiUrl}/credits/${accountId}`, {
      "creditAmount": ammount,
      "currency": "USD"
    }, httpOptions);
  }

  withdrawMoney(accountId: string, withdrawDTO: WithdrawMoney) {
    return this.http.post(`${this.userApiUrl}/accounts/${accountId}/withdraw`, withdrawDTO);
  }
}
