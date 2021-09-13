import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 loggedIn:boolean=true;
   get email() {
    return this.loginForm!.get('email');
  }
  get password() {
    return this.loginForm!.get('password');
  }

  constructor(private fb:FormBuilder,private auth:AuthService,
    private router:Router
    ) { }

  ngOnInit(): void {
  }
  loginForm = this.fb.group({
    email:['',[Validators.required]],
    password:['',[Validators.required]]
  });
  loggingin(){
    this.auth.login(this.loginForm.value)
    .subscribe(data => {
      // if(data!==null){
      localStorage.setItem("jwttoken",data.jwtToken);
     
    //  console.log(data)
    this.auth.getUserByEmail(this.loginForm.get('email')?.value)
    .subscribe(data => {
      localStorage.setItem("userId",data.id);
      //console.log(data.id);
      localStorage.setItem("userInfo",JSON.stringify(data));
      localStorage.setItem("userRole",data.role);
      this.router.navigate(['/products']);
      
    });
      //}
  },err =>{
    this.loggedIn=false;
  });
}

}
