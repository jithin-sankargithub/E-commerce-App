import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { OrderService } from './order.service';
import { HttpClient } from '@angular/common/http';
import { Order } from '../Models/order';

describe('OrderService', () => {
  let service: OrderService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [HttpClientTestingModule],
      providers : [OrderService]
    });
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(OrderService);
  });

  afterEach(() => {
    httpTestingController.verify(); //Verifies that no requests are outstanding.
  });

 describe('getUserOrders', () => {
   let expectedOrders:Order[];

   beforeEach(() => {
     expectedOrders = [{
       id:"61308320b9058a064ad69391",
       userId:"6130453477921e433b4363a5",
       cartItems:[{
         productId:"434232322",
         name:"Iphone",
         category:"Electornics",
         description:"new phone",
         price:30000,
         image:"img.jpg",
         quantity:2
       }],
       totalPrice:30000,
       address:{
         houseNumber:"11A",
         street:"11Q",
         city:"A21",
         state:"Kl",
         pincode:"112121"
       },
       paymentType:"WALLET",
       transactionId:"2313132132"
     }] as Order[];
   });
   it('should return expected orders by calling once', () =>{
     const userId = "6130453477921e433b4363a5";
     service.getUserOrder(userId).subscribe(
       data => expect(data).toEqual(expectedOrders,'Should return expected orders'),
       fail
     );
     const req = httpTestingController.expectOne("http://localhost:8080/order/orders/"+userId);
     expect(req.request.method).toEqual('GET');
     req.flush(expectedOrders);
   })
 });
});
