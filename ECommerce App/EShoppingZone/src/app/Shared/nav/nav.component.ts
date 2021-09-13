import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  userId!:string;
  userRole!:string;
  constructor(private authService:AuthService,private router:Router) { }

  ngOnInit(): void {
    this.authService.userId.subscribe(data => this.userId=data);
    this.authService.role.subscribe(data =>this.userRole=data);
    
  }
  logout(){
    this.authService.emitUserId('');
    this.authService.emitRole('');
    this.router.navigate(['/']);
    localStorage.clear();
    location.reload();
  }
}
