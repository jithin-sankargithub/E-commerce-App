import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/Models/transaction';
import { WalletService } from 'src/app/Services/wallet.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  userId:string='';
  transactions:Transaction[]=[];

  constructor(private walletSetvice:WalletService,private router:Router) { }

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.getTransactions();
    if(!this.userId){
      this.router.navigate(['/products']);
    }
  }
  getTransactions(){
    this.walletSetvice.getTransactions(this.userId)
    .subscribe(data => this.transactions=data);
    console.log(this.transactions);

  }

}
