export class Transaction {
    id:string;
    sender:string;
    receiver:string;
    amount:number;
    constructor(id:string,sender:string,receiver:string,amount:number){
        this.id=id;
        this.sender=sender;
        this.receiver=receiver;
        this.amount=amount;
    }
}
