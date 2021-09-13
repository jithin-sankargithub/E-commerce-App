import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'
import { OrderListComponent } from './order-list.component';
import { HttpClient } from '@angular/common/http';
import { OrderService } from 'src/app/Services/order.service';
import { OrderComponent } from '../order/order.component';
import { FormBuilder } from '@angular/forms';

describe('OrderListComponent', () => {
  let component: OrderListComponent;
  let fixture: ComponentFixture<OrderListComponent>;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ OrderListComponent ],
      providers : [OrderService,FormBuilder]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 
  it('should do getUserOrder method call', () => {
    const service = TestBed.get(OrderService);
    const spyOnMethod = spyOn(service,'getUserOrder').and.callThrough();
    component.getAllorders();
  
    expect(spyOnMethod).toHaveBeenCalled();
  });
});
