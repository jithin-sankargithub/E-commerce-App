import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,of } from 'rxjs';
import { Product } from '../Models/product';
import { catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  getProductList(): Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:8080/product/getallproducts")
    .pipe(catchError(this.handleError<Product[]>('invalid', [])));
  }
  getProductListByCategory(category:any): Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:8080/product/getproductbycategory/"+category)
    .pipe(catchError(this.handleError<Product[]>('invalid', [])));
  }
  private handleError<T>(operation = 'operation', result?: T){
    return (error :any): Observable<T> => {
      console.log(error);

      console.log(`${operation}  failed: ${error.message}` );
      return of(result as T);
    }
  }
  deleteProduct(productId:string){
    console.log(productId);
    return this.http.delete("http://localhost:8080/product/deleteproduct/"+productId);

  }
  addProduct(product:Product):Observable<Product>{
   return this.http.post<Product>("http://localhost:8080/product/addproduct",product);
  }
  getProductById(productId:string):Observable<Product>{
    return this.http.get<Product>("http://localhost:8080/product/getproductbyid/"+productId);
  }
  updateProduct(product:Product){
   return this.http.put("http://localhost:8080/product/updateproduct",product);

  }
}
