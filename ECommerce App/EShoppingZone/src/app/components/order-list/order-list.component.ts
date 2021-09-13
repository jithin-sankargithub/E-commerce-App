import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Order } from 'src/app/Models/order';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  userid?:string;
  orderItems:Order[]=[];

  constructor(private orderService:OrderService,private router:Router) { }

  ngOnInit(): void {
    this.userid=localStorage.getItem("userId")!;
    if(!this.userid){
      this.router.navigate(['/products']);
    }
    this.getAllorders();
  }
  getAllorders(){
    this.orderService.getUserOrder(this.userid!)
    .subscribe(data => {
      this.orderItems=data;
      //console.log(this.orderItems);
    });
    

  }

}
