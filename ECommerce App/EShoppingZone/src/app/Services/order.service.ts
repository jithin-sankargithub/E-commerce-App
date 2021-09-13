import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../Models/cart';
import { Order } from '../Models/order';
import { Ordercheckout } from '../Models/ordercheckout';
import { Wallet } from '../Models/wallet';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) { }
  getCart(userId:string):Observable<Cart>{
    return this.http.get<Cart>("http://localhost:8080/cart/getcartbyuserid/"+userId);

  }
  getWalletBalance(userId:string):Observable<Wallet>{
    return this.http.get<Wallet>("http://localhost:8080/wallet/findwallet/"+userId);
  }
  placeOrder(order:Ordercheckout):Observable<any>{
    return this.http.post<any>("http://localhost:8080/cart/checkout",order);

  }
  getUserOrder(userId:string):Observable<Order[]>{
    return this.http.get<Order[]>("http://localhost:8080/order/orders/"+userId);
  }
}
