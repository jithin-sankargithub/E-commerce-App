import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/Models/product';
import { DialogService } from 'src/app/Services/dialog.service';
import { ProductService } from 'src/app/Services/product.service';

@Component({
  selector: 'app-merchant',
  templateUrl: './merchant.component.html',
  styleUrls: ['./merchant.component.css']
})
export class MerchantComponent implements OnInit {
  productList : Product[]=[];
  userId:string='';
  userRole:string='';

  constructor(private _productService:ProductService,private router:Router,
                private dialogService:DialogService) { }

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    if(!this.userId){
      this.router.navigate(['/products']);
    }
    if(this.userRole=="customer"){
      this.router.navigate(['/products']);
    }
    this.getAllProducts();
  }
  getAllProducts(){
   
    this._productService.getProductList()
    .subscribe(data => this.productList=data);
    console.log(this.productList);
   }
   deleteProduct(productId:string,productName:string,productCategory:string){
   
     this.dialogService.openConfirmDialog(`Are you sure to delete this item ${productName} of ${productCategory} ?`)
     .afterClosed().subscribe(res =>{
       if(res){
         this._productService.deleteProduct(productId)
         .subscribe(data => console.log(data));
         this.ngOnInit();
       }
     });
   }
   updateProduct(productId:string){
     this.router.navigate(['/updateproduct',productId]);

   }
   

}
