import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { CartComponent } from './components/cart/cart.component';
import { NavComponent } from './Shared/nav/nav.component';
import { FilterComponent } from './Shared/filter/filter.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CategoryComponent } from './components/category/category.component';
import { MerchantComponent } from './components/merchant/merchant.component';
import { AddProductComponent } from './components/merchant/add-product/add-product.component';
import { LoginComponent } from './components/login/login.component';
import { OrderComponent } from './components/order/order.component';
import { RegisterComponent } from './components/register/register.component';
import { CustomerProfileComponent } from './components/customer-profile/customer-profile.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { OrderListComponent } from './components/order-list/order-list.component'
import { TokenService } from './Services/token.service';
import { SuccessComponent } from './components/success/success.component';
import { TransactionsComponent } from './components/wallet/transactions/transactions.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { MatConfirmDialogComponent } from './mat-confirm-dialog/mat-confirm-dialog.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    CartComponent,
    NavComponent,
    FilterComponent,
    CategoryComponent,
    MerchantComponent,
    AddProductComponent,
    LoginComponent,
    OrderComponent,
    RegisterComponent,
    CustomerProfileComponent,
    WalletComponent,
    OrderListComponent,
    SuccessComponent,
    TransactionsComponent,
    MatConfirmDialogComponent,
    UpdateProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [{
    provide:HTTP_INTERCEPTORS,
    useClass:TokenService,
    multi:true
  }],
  bootstrap: [AppComponent],
  entryComponents : [MatConfirmDialogComponent]
})
export class AppModule { }
