import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/Models/user';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {
  userId:string='';
  userDetails?:User;
  updateDetails:User={id:'',userName:'',password:'',email:'',mobileNo:'',address:{houseNumber:'',street:'',city:'',state:'',pincode:''},role:'customer'};
  userRole:string='';

  get userName() {
    return this.updateForm.get('userName');
  }
  get password() {
    return this.updateForm.get('password');
  }
  get email() {
    return this.updateForm.get('email');
  }
  get mobileNo() {
    return this.updateForm.get('mobileNo');
  }
  constructor(private userService:UserService,private fb:FormBuilder, private router:Router) { }

  updateForm=this.fb.group({
    userName:['',[Validators.required]],
    password:['',[Validators.required]],
    email:['',[Validators.required]],
    mobileNo:['',[Validators.required,Validators.maxLength(10),Validators.minLength(10),Validators.pattern("^[0-9]*$")]],
    address :this.fb.group({
      houseNumber:[''],
      street:[''],
      city:[''],
      state:[''],
      pincode:['']
    })
  })
  
  ngOnInit(): void {
    this.userId=localStorage.getItem("userId")!;
    this.userRole=localStorage.getItem("userRole")!;
    this.getUserById();
    if(!this.userId){
      this.router.navigate(['/products']);
    }
    if(this.userRole=='merchant'){
      this.router.navigate(['/products']);
    }
    
  }
  getUserById(){
    this.userService.getUserById(this.userId)
    .subscribe(data => {this.userDetails=data
      //test
    //console.log(this.userDetails?.email);
    this.updateForm.patchValue(this.userDetails);
    })
    }
    update(){
      this.updateDetails.id=this.userId;
      this.updateDetails.userName=this.updateForm.get('userName')?.value;
      this.updateDetails.password=this.updateForm.get('password')?.value;
      this.updateDetails.email=this.updateForm.get('email')?.value;
      this.updateDetails.mobileNo=this.updateForm.get('mobileNo')?.value;
      this.updateDetails.address=this.updateForm.get('address')?.value;
      this.userService.updateUser(this.updateDetails)
      .subscribe(data => {
        window.alert("Profile updated");
        this.router.navigate(['/']);});
      
     }
   
 
}
