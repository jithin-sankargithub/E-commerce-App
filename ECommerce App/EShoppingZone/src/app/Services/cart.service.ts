import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Cart } from '../Models/cart';
import { CartItem } from '../Models/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartProducts:CartItem[]=[];
  totalPrice:Subject<number>=new Subject<number>();
  totalQuantity:Subject<number>=new Subject<number>();

  constructor(private http:HttpClient) { 
    this.cartProducts=localStorage.getItem("cartkey") != null  ? JSON.parse(localStorage.getItem("cartkey")!) : [];
  }

  addToCart(TheCartProduct:CartItem){
    
    //To check if product already exist in cart
    let presentInCart :boolean=false;
    let availableCartProduct = undefined;

    if(this.cartProducts.length>0){

      //Searching if item is available based on id
      //Using find method
         availableCartProduct=this.cartProducts.find( item => item.productId === TheCartProduct.productId);

      //Checking if the item is matched
        presentInCart=(availableCartProduct!=undefined);
      }

    if(presentInCart){

      // Adding the quantity
      availableCartProduct!.quantity++
    }
    else{
      // Add the product to the cartitem array
      this.cartProducts.push(TheCartProduct);
    }
    //setting the array of cartProducts to local storage
    localStorage.setItem("cartkey",JSON.stringify(this.cartProducts));

   
  }

  calculateTotals(){
    //Initializing the varibles to zero to calculate total price and quantity
    let endTotal :number=0;
    let endQuantity:number=0;

    for(let item of this.cartProducts){
      endTotal+= item.quantity*item.price;
      endQuantity+=item.quantity;
    }
    //Using subject emitting the values to cartComponent using next method
    this.totalPrice.next(endTotal);
   this.totalQuantity.next(endQuantity);
  }

  reducingQuantity(CartProduct:CartItem){

    this.cartProducts.forEach(item => {
      //condition to check product is same by using id
      if(item.productId===CartProduct.productId){
        //Reducing the item quantity by one
        item.quantity--;
        //Checking if quantity of particular item is zero inorder to remove it from cart
        if(item.quantity===0){
          this.removeFromCart(CartProduct);
        }
      }
    })
    localStorage.setItem("cartkey",JSON.stringify(this.cartProducts));
    
  }
   //Method to remove item from cart if the quantity is zero
  removeFromCart(CartProduct:CartItem){
    //finding the index of the particular product from the array
    const index = this.cartProducts.findIndex(item => item.productId===CartProduct.productId);
    if(index > -1){
      this.cartProducts.splice(index,1);
    }
  }

  moveToCheckout(userCart:Cart):Observable<any>{
    console.log(userCart.cartItems);
   return this.http.post("http://localhost:8080/cart/newcart",userCart);
  }
}
