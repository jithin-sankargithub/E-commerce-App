import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,of } from 'rxjs';
import { Wallet } from '../Models/wallet';
import { catchError} from 'rxjs/operators';
import { RechargeWallet } from '../Models/recharge-wallet';
import { Transaction } from '../Models/transaction';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  constructor(private http:HttpClient) { }
  findUserWallet(userId:string) :Observable<Wallet>{
    return this.http.get<Wallet>("http://localhost:8080/wallet/findwallet/"+userId)
    .pipe(catchError(this.handleError<Wallet>('invalid')));
  }
  private handleError<T>(operation = 'operation', result?: T){
    return (error :any): Observable<T> => {
      console.log(error);

      console.log(`${operation}  failed: ${error.message}` );
      return of(result as T);
    }
  }
  createWallet(createdWallet:Wallet){
    console.log(createdWallet);

    return this.http.post("http://localhost:8080/wallet/create",createdWallet,{responseType:'text' as 'json'});
  }
  rechargeWallet(rechargeWallet:RechargeWallet){
    console.log(rechargeWallet);
    return this.http.post("http://localhost:8080/wallet/rechargewallet",rechargeWallet)

  }
  getTransactions(userId:string):Observable<Transaction[]>{
    return this.http.get<Transaction[]>("http://localhost:8080/transaction/gettransactions/"+userId);
  }
}
