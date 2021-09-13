export class Address {

    houseNumber:string;
    street:string;
    city:string;
    state:string;
    pincode:string;
    constructor(houseNumber:string,street:string,city:string,state:string,pincode:string){
        this.houseNumber=houseNumber;
        this.street=street;
        this.city=city;
        this.state=state;
        this.pincode=pincode;
    }
}
