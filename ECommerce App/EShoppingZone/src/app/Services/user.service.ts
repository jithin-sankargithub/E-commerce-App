import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  registerCustomer(data:User):Observable<User>{
    return this.http.post<User>("http://localhost:8080/user/adduser",data);

  }
 getUserById(userId:string):Observable<User>{
   return this.http.get<User>("http://localhost:8080/user/finduser/"+userId);
 }
 updateUser(data:User):Observable<any>{
   return this.http.put("http://localhost:8080/user/edituser",data);
 }
}
