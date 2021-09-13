import { Product } from "./product";

export class CartItem {

    productId:string;
    name:string;
    category:string;
    description:string;
    price:number;
    image:string;
    quantity:number;

    constructor(product:Product){
        
        this.productId=product.id;
        this.name=product.name;
        this.category=product.category;
        this.description=product.description;
        this.price=product.price;
        this.image=product.image;
        this.quantity=1;

    }
}
