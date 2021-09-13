import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RechargeWallet } from 'src/app/Models/recharge-wallet';
import { Wallet } from 'src/app/Models/wallet';
import { WalletService } from 'src/app/Services/wallet.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit {
  
  userId:string='';
  customerWallet?:Wallet;
  userRole:string='';
  
  
  get amount() {
    return this.userWallet!.get('amount');
  }

  constructor(private walletService:WalletService,private fb:FormBuilder,
               private router:Router) { }
  userWallet = this.fb.group({
    amount : ['',[Validators.required,Validators.pattern("^[0-9]*$")]],
   });
   rechargeForm = this.fb.group({
     amount : ['',[Validators.required,Validators.pattern("^[0-9]*$")]],
    });

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    this.findWallet(this.userId);
    if(!this.userId){
      this.router.navigate(['/products']);
    }
    if(this.userRole=='merchant'){
      this.router.navigate(['/products']);
    }
  }
  findWallet(userId:string){
    console.log(this.userId);
    this.walletService.findUserWallet(this.userId)
    .subscribe(data => this.customerWallet=data);
    console.log(this.customerWallet);
  }
  createWallet(){
    let deposit=this.userWallet.get('amount')?.value;
    const createdWallet:Wallet = new Wallet("",this.userId,deposit);
    this.walletService.createWallet(createdWallet).subscribe(data => console.log(data));
    console.log(createdWallet);
    window.alert("EShoppingZone wallet created")
    this.router.navigate(['/useraccount']);
  }
  rechargeWallet(){
    let topupamount=this.rechargeForm.get('amount')?.value;
    console.log(topupamount);
    const rechargeWallet:RechargeWallet=new RechargeWallet(this.userId,topupamount);
    this.walletService.rechargeWallet(rechargeWallet)
    .subscribe(data => console.log(data));
    console.log(rechargeWallet);
    window.alert("Wallet recharged");
    //this.ngOnInit();
    this.router.navigate(['/useraccount']);
    }

}
