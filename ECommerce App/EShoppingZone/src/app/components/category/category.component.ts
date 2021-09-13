import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/Models/cart-item';
import { Product } from 'src/app/Models/product';
import { CartService } from 'src/app/Services/cart.service';
import { ProductService } from 'src/app/Services/product.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  productList : Product[]=[];
  currentCategory!:string;

  constructor( private _productService:ProductService, 
    private _cartService: CartService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getProductsByCategory();
      });
  }
  getProductsByCategory(){
    this.currentCategory=this.route.snapshot.paramMap.get('name') || '';
    this._productService.getProductListByCategory(this.currentCategory)
    .subscribe(data => this.productList=data);
    //console.log(this.productList);
   }

   addToCart(AddedProduct:Product){
     //Storing added cart product in const CartProduct
     const CartProduct: CartItem = new CartItem(AddedProduct);
     this._cartService.addToCart(CartProduct);
   }

}
