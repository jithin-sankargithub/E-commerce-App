import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { CategoryComponent } from './components/category/category.component';
import { CustomerProfileComponent } from './components/customer-profile/customer-profile.component';
import { LoginComponent } from './components/login/login.component';
import { AddProductComponent } from './components/merchant/add-product/add-product.component';
import { MerchantComponent } from './components/merchant/merchant.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { OrderComponent } from './components/order/order.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { RegisterComponent } from './components/register/register.component';
import { SuccessComponent } from './components/success/success.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { TransactionsComponent } from './components/wallet/transactions/transactions.component';
import { WalletComponent } from './components/wallet/wallet.component';

const routes: Routes = [
  {path :'category/:name', component:CategoryComponent},
  {path : 'products', component:ProductListComponent},
  {path : 'cart', component:CartComponent},
  {path : 'useraccount', component:CustomerProfileComponent},
  {path : 'wallet', component:WalletComponent},
  {path : 'wallettransactions', component:TransactionsComponent},
  {path : 'merchant', component:MerchantComponent},
  {path : 'addproduct', component:AddProductComponent},
  {path : 'updateproduct/:id', component:UpdateProductComponent},
  {path : 'login', component:LoginComponent},
  {path : 'register', component:RegisterComponent},
  {path : 'order', component:OrderComponent},
  {path : 'orderlist', component:OrderListComponent},
  {path : 'success', component:SuccessComponent},
  {path:'',component: ProductListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
