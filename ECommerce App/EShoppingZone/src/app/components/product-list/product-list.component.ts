import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Product } from 'src/app/Models/product';
import { AuthService } from 'src/app/Services/auth.service';
import { CartService } from 'src/app/Services/cart.service';
import { ProductService } from 'src/app/Services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  productList : Product[]=[];
  //currentCategory!:string;
  userId:string='';
  userRole:string='';


  constructor(
    private _productService:ProductService, 
    private _cartService: CartService,
    //private route: ActivatedRoute,
    private authService:AuthService
  ) { }

  ngOnInit(): void {
     this.userId=localStorage.getItem("userId")!;
     this.userRole=localStorage.getItem("userRole")!;
     this.authService.emitUserId(this.userId);
     this.authService.emitRole(this.userRole);
     this.getAllProducts();
  }
  getAllProducts(){
    this._productService.getProductList()
    .subscribe(data => this.productList=data);
    //console.log(this.productList);
      
   }
  //  getProductImg(productName:string){
  //    return "assets/images/"+productName+".jpg";
  //  }

   addToCart(AddedProduct:Product){
     //Storing added cart product in const CartProduct
     const CartProduct: CartItem = new CartItem(AddedProduct);
     this._cartService.addToCart(CartProduct);
   }
   

}
