import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { ProductService } from './product.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Product } from '../Models/product';


describe('ProductService', () => {
  let service: ProductService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports : [HttpClientTestingModule],
      providers : [ProductService]
    });
 

  httpClient = TestBed.inject(HttpClient);
  httpTestingController = TestBed.inject(HttpTestingController);
  service = TestBed.inject(ProductService);
});

afterEach(() => {
  httpTestingController.verify(); //Verifies that no requests are outstanding.
});
describe('getAllProducts', () => {
  let allProducts:Product[];

  beforeEach(() => {
    allProducts = [{
      id:"61212b0aea003700a07a3d35",
      name: "Iphone X",
      category : "Electronics",
      description: "The new iphone X with 5G",
      price : 20000,
      image : "https://image.shutterstock.com/image-photo/uzhgorod-ukraine-november-10-2017-260nw-752861248.jpg"
    },
  {
    id:"61212b4aea003700a07a3d37",
    name: "Redmi note 9 pro",
    category : "Electronics",
    description: "The new redmi note 9 pro  with 64gb storage",
    price : 20000,
    image : "https://fdn.gsmarena.com/imgroot/reviews/20/xiaomi-redmi-note-9-pro/lifestyle/-727w2/gsmarena_005.jpg"
  }] as Product[];
  });

  it('Should return all products by calling method',() => {
    service.getProductList().subscribe(data => expect(data).toEqual(allProducts,'should return expected products'),
    fail
    );
    const request = httpTestingController.expectOne("http://localhost:8080/product/getallproducts");
    expect(request.request.method).toEqual('GET');
    request.flush(allProducts);
  })
  it('should be Ok returning no products',() =>{
    service.getProductList().subscribe(
      data => expect(data.length).toEqual(0,'should have empty products array'),
      fail
    );
    const request = httpTestingController.expectOne("http://localhost:8080/product/getallproducts");
    request.flush([]);
  })
});
xit('should add a product', () => {
  const prod:Product={id:"61212b0aea003700a07a3d35",
                      name:"Iphone X",
                     category:"Electronics",
                      description:"The new iphone X with 5G",
                      price:20000,
                     image:"product.jpg"};
   service.addProduct(prod)
   .subscribe( data => expect(data).toEqual(prod,'Should return product'),fail);
   
   const req = httpTestingController.expectOne("http://localhost:8080/product/addproduct");
   expect(req.request.method).toEqual('POST');
   expect(req.request.body).toEqual('prod');

    const expectedResponse = new HttpResponse({ status: 201, statusText: 'Created', body: prod });
    req.event(expectedResponse);
})
});
