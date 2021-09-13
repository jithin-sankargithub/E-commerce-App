import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { ProductListComponent } from './product-list.component';
import { ProductService } from 'src/app/Services/product.service';
import { Product } from 'src/app/Models/product';
import * as Rx from 'rxjs';
import { delay } from 'rxjs/operators';


describe('ProductListComponent', () => {
  let component: ProductListComponent;
  let fixture: ComponentFixture<ProductListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ ProductListComponent ],
      providers : []
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });



  xit('sholud call getProductList and get response as array',fakeAsync(() => {
    fixture = TestBed.createComponent(ProductListComponent);
    component = fixture.debugElement.componentInstance;
     const service = fixture.debugElement.injector.get(ProductService);
     let spy_getProductList = spyOn(service, "getProductList").and.callFake(() => {
       return Rx.of([{id: "658388d8f838ff",
                      name: "Iphone X",
                      category: "Electronics",
                      description: "The new Iphone X",
                      price:20000,
                      image:"phone.jpg"  
      }]).pipe(delay(2000));
  });
  component.getAllProducts();
  tick(2000);
  expect(component.getAllProducts).toEqual([{id: "658388d8f838ff",
  name: "Iphone X",
  category: "Electronics",
  description: "The new Iphone X",
  price:20000,
  image:"phone.jpg" }]);
}));
it('should do getProductList method call', () => {
  const service = TestBed.get(ProductService);
  const spyOnMethod = spyOn(service,'getProductList').and.callThrough();
  component.getAllProducts();

  expect(spyOnMethod).toHaveBeenCalled();
});


});
