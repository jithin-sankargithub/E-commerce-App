import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { User } from '../Models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  userId:Subject<string> = new Subject<string>();
  role:Subject<string> = new Subject<string>();

  constructor(private http:HttpClient) { }
  emitUserId(userId:string){
    this.userId.next(userId);
  }
  emitRole(role:string){
    this.role.next(role);
  }

  login(data:any):Observable<any>{
    return this.http.post<any>("http://localhost:8080/authenticate",data);
  }
  getUserByEmail(email:string):Observable<User>{
    return this.http.get<User>("http://localhost:8080/user/finduserbyemail/"+email);
  }
}
