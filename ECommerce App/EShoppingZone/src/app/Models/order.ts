import { Address } from "./address";
import { CartItem } from "./cart-item";

export class Order {
    id:string;
    userId:string;
    cartItems:CartItem[];
    totalPrice:number;
    address:Address;
    paymentType:string;
    transactionId:string;
    constructor(id:string,userId:string,cartItems:CartItem[],totalPrice:number,address:Address,paymentType:string,
        transactionId:string){
            this.id=id;
            this.userId=userId;
            this.cartItems=cartItems;
            this.totalPrice=totalPrice;
            this.address=address;
            this.paymentType=paymentType;
            this.transactionId=transactionId;
        }
}
