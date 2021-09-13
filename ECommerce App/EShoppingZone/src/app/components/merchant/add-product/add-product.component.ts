import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { ProductService } from 'src/app/Services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  product!:Product;
  userId:string='';
  userRole:string='';

  get name() {
    return this.productForm.get('name');
  }
  get category() {
    return this.productForm.get('category');
  }
  get description() {
    return this.productForm.get('description');
  }
  get price() {
    return this.productForm.get('price');
  }
  get image() {
    return this.productForm.get('image');
  }

  constructor(private fb:FormBuilder,private productService:ProductService,
               private router:Router) { }

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    if(!this.userId){
      this.router.navigate(['/products']);
    }
    if(this.userRole=="customer"){
      this.router.navigate(['/products']);
    }
  }
  productForm = this.fb.group({
    name: ['',[Validators.required]],
    category: ['',[Validators.required]],
    description: ['',[Validators.required]],
    price: ['',[Validators.required,Validators.pattern("^[0-9]*$")]],
    image: [''] 
  });


  addProduct(){
    this.product=this.productForm.value;
    console.log(this.product);
    this.productService.addProduct(this.product)
    .subscribe(data => window.alert("Product added"));
     this.router.navigate(['/merchant']);

  }


}
