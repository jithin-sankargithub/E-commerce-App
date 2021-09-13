import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/Services/cart.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {
  userId:string='';

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    if(!this.userId){
      this.router.navigate(['/products']);
    }
  }

}
