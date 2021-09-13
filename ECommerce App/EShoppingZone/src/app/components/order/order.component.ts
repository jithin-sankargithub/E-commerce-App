import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Ordercheckout } from 'src/app/Models/ordercheckout';
import { Wallet } from 'src/app/Models/wallet';
import { CartService } from 'src/app/Services/cart.service';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  userId?:string="";
  cartList:CartItem[]=[];
  totalPrice=0;
  walletBalance:number=0;
  balanceCheck:boolean=false;
  walletExist:boolean=false;
  paymentType:string="";
  walletInfo?:Wallet;

  get houseNumber() {
    return this.checkoutForm.get('houseNumber');
  }
  get street() {
    return this.checkoutForm.get('street');
  }
  get city() {
    return this.checkoutForm.get('city');
  }
  get state() {
    return this.checkoutForm.get('state');
  }
  get pincode() {
    return this.checkoutForm.get('pincode');
  }
  get payment() {
    return this.checkoutForm.get('payment');
  }

  constructor(private orderService:OrderService,
            private _cartService:CartService,
            private fb:FormBuilder,
            private router:Router) {}

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.getCart();
    this.getWalletBalance();
    if(!this.userId){
      this.router.navigate(['/products']);
    }
}
  checkoutForm = this.fb.group({
    houseNumber: ['',[Validators.required]],
    street: ['',[Validators.required]],
    city: ['',[Validators.required]],
    state: ['',[Validators.required]],
    pincode: ['',[Validators.required]],
  });
  paymentform = this.fb.group({
    payment:['COD']
 });
  getCart(){
    this.orderService.getCart(this.userId!)
    .subscribe(data =>{ this.cartList=data.cartItems;
      this.totalPrice=data.totalPrice;
      console.log(this.totalPrice);
      console.log(this.cartList);
    });
    
  }
  getWalletBalance(){
    
    this.orderService.getWalletBalance(this.userId!)
    .subscribe(data =>{ this.walletInfo=data;
      this.walletBalance=this.walletInfo.balance;
              if(this.walletBalance<this.totalPrice){
                this.balanceCheck=true;
                console.log(this.balanceCheck);
              
              } 
            });
            this.getCart();
  }
   checkout(){
     this.paymentType=this.paymentform.get('payment')!.value;
    //  console.log(this.paymentform.get('payment')!.value);
    //  console.log(this.paymentType);
     const order = new Ordercheckout(this.userId!,this.paymentType,this.checkoutForm.value);
    //  console.log(this.checkoutForm.value);
    //  console.log(order);
     this.orderService.placeOrder(order)
     .subscribe(data=> console.log(data));
     //to clean local storage
     localStorage.removeItem("cartkey");
     this._cartService.cartProducts=[];
     this.router.navigate(['/success']);
   }

}
