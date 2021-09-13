import { CartItem } from "./cart-item";

export class Cart {

    id:string;
    userId:string;
    cartItems:CartItem[];
    totalPrice:number;
    constructor(id:string,userId:string,cartItems:CartItem[],totalPrice:number){
        this.id=id;
        this.userId=userId;
        this.cartItems=cartItems;
        this.totalPrice=totalPrice;
    }
}
