import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Order } from '../dto/order';
import { CreateOrderDto } from '../dto/create.order';
import { StockProjection } from '../dto/stock.projection';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  private apiUrlOrders: string = `${environment.apiUrl}/api/orders`

  constructor(private http: HttpClient) { }

  getOrdersForUser(accountId: string): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrlOrders}/account/${accountId}`)
  }

  createNewOrder(order: CreateOrderDto) {
    return this.http.post(`${this.apiUrlOrders}/new-order`, order, {responseType: 'text'})
    // throw new Error("Method not implemented.");
  }

  closeOrder(orderInfo: StockProjection){
    return this.http.put(`${this.apiUrlOrders}/close/${orderInfo.orderId}`,orderInfo);
  }

  getOrderCount(accountId: string){
    return this.http.get(`${this.apiUrlOrders}/account/${accountId}/order-count`);
  }

}
