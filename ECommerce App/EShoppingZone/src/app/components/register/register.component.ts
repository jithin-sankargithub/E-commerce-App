import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { UserService } from 'src/app/Services/user.service';
import { PasswordValidator } from 'src/app/Shared/password.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userRegister:User={id:'',userName:'',password:'',email:'',mobileNo:'',address:{houseNumber:'',street:'',city:'',state:'',pincode:''},role:'customer'}
  registerForm! : FormGroup;
 


  get userName() {
    return this.registerForm.get('userName');
  }
  get password() {
    return this.registerForm.get('password');
  }
  get email() {
    return this.registerForm.get('email');
  }
  get mobileNo() {
    return this.registerForm.get('mobileNo');
  }

  constructor(private fb:FormBuilder,private userService:UserService,
                private router:Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      userName: ['',[Validators.required]],
      password: ['',[Validators.required]],
      confirmPassword:[''],
      email: ['',[Validators.required]],
      mobileNo: ['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.minLength(10),Validators.maxLength(10)]]
    },{validators: PasswordValidator});
  }
  register(){
    this.userRegister.userName=this.registerForm.get('userName')?.value;
    this.userRegister.password=this.registerForm.get('password')?.value;
    this.userRegister.email=this.registerForm.get('email')?.value;
    this.userRegister.mobileNo=this.registerForm.get('mobileNo')?.value;
    this.userService.registerCustomer(this.userRegister)
    .subscribe(data => window.alert("Registration Successful"));
    this.router.navigate(['/login']);
 
  }

}
