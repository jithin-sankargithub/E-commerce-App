import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { ProductService } from 'src/app/Services/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  productId:string='';
  productDetails!:Product;
  productUpdate:Product={id:'',name:'',category:'',description:'',price:0,image:''};
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


  constructor(private router:ActivatedRoute,
              private productService:ProductService,
               private fb:FormBuilder,
               private routeNav:Router) { }

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    if(!this.userId){
      this.routeNav.navigate(['/products']);
    }
    if(this.userRole=="customer"){
      this.routeNav.navigate(['/products']);
    }
    this.productId=this.router.snapshot.params['id'];
    this.getProductDetail();
    
  }
  productForm = this.fb.group({
    name: ['',[Validators.required]],
    category: ['',[Validators.required]],
    description: ['',[Validators.required]],
    price: ['',[Validators.required,Validators.pattern("^[0-9]*$")]],
    image: [''] 
  });
  getProductDetail(){
    this.productService.getProductById(this.productId)
    .subscribe(data => {
      this.productDetails=data
    this.productForm.patchValue(this.productDetails)});
  }
  updateProduct(){
    this.productUpdate.id=this.productId;
    this.productUpdate.name=this.productForm.get('name')?.value;
    this.productUpdate.category=this.productForm.get('category')?.value;
    this.productUpdate.description=this.productForm.get('description')?.value;
    this.productUpdate.price=this.productForm.get('price')?.value;
    this.productUpdate.image=this.productForm.get('image')?.value;
    this.productService.updateProduct(this.productUpdate)
    .subscribe(data => console.log(data));
    this.routeNav.navigate(["/merchant"])
  }

}
