import { Iaddress } from "./regaddress";

export interface User {
    id:string;
    userName:string;
    password:string;
    email:string;
    mobileNo:string;
    address:Iaddress;
    role:string;

}
