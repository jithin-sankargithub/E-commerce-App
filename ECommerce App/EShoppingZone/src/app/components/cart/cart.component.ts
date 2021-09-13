import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/Models/cart';
import { CartItem } from 'src/app/Models/cart-item';
import { AuthService } from 'src/app/Services/auth.service';
import { CartService } from 'src/app/Services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  addedCartProducts:CartItem[]=[];
  totalPrice=0;
  totalQuantity=0;
  userId!:string;
  userRole!:string;

  constructor(private _cartService:CartService,
              private router : Router,
              private authService:AuthService) { }

  ngOnInit(): void {
    this.cartedProductlist();
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    this.authService.emitUserId(this.userId);
    this.authService.emitRole(this.userRole);
  }

  cartedProductlist(){
    //Accessing carted products from local storage and storing it to array
    this.addedCartProducts=JSON.parse(localStorage.getItem("cartkey")!);
    console.log(this.addedCartProducts);
     // Subscribing from the next method
    this._cartService.totalPrice.subscribe(data => this.totalPrice=data);

    //this._cartService.totalQuantity.subscribe(data => this.totalQuantity=data);

    // Calculating the cart total and quantity
    this._cartService.calculateTotals();
      
  }
    addQuantity(CartProduct:CartItem){
      this._cartService.addToCart(CartProduct);
      this.cartedProductlist();

    }
    reduceQuantity(CartProduct:CartItem){
      this._cartService.reducingQuantity(CartProduct);
      this.cartedProductlist();
    }

    moveToCheckout(){
      const cart:Cart=new Cart("",this.userId,this.addedCartProducts,this.totalPrice);
      //test
      // console.log(cart.totalPrice);
      // console.log(cart.cartItems);
      this._cartService.moveToCheckout(cart).subscribe(data =>console.log(data));
      this.router.navigate(["/order"]);
      }
    

}
