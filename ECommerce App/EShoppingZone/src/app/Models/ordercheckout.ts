import { Address } from "./address";

export class Ordercheckout {
    userId:string;
    paymentType:string;
    address:Address;
    constructor(userId:string,paymentType:string,address:Address){
        this.userId=userId;
        this.paymentType=paymentType;
        this.address=address;
    }
}
