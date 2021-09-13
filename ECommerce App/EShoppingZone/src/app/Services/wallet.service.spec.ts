import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { WalletService } from './wallet.service';
import { HttpClient } from '@angular/common/http';
import { Transaction } from 'src/app/Models/transaction';

describe('WalletService', () => {
  let service: WalletService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [HttpClientTestingModule],
      providers : [WalletService]
    });
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(WalletService);
  });
  afterEach(() => {
    httpTestingController.verify(); //Verifies that no requests are outstanding.
  });
describe('getUserTransactions', () =>{
  let userTransactions: Transaction[];
  beforeEach(() => {
    userTransactions = [{
      id:"6323323232dwd444ef4554",
      sender:"66227323823838233r3e3",
      receiver:"EShoppingZone",
      amount:3000
    },
  {
    id:"63433en32enenj3n3n2n323",
    sender:"66227323823838233r3e3",
    receiver:"EShoppingZone",
    amount:6000
  }] as Transaction[];
  });
  it('Should return expected transaction by user', () => {
    const userId= "66227323823838233r3e3";
    service.getTransactions(userId).subscribe(
      data => expect(data).toEqual(userTransactions,'should return expected transactions'),
      fail
    );
    const req = httpTestingController.expectOne("http://localhost:8080/transaction/gettransactions/"+userId);
    expect(req.request.method).toEqual('GET');
    req.flush(userTransactions);
  })
})

 
});
