import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { CustomerProfileComponent } from './customer-profile.component';
import { FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/Services/user.service';

describe('CustomerProfileComponent', () => {
  let component: CustomerProfileComponent;
  let fixture: ComponentFixture<CustomerProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ CustomerProfileComponent ],
      providers: [FormBuilder]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 
  it('should do getUserById method call', () => {
    const service = TestBed.get(UserService);
    const spyOnMethod = spyOn(service,'getUserById').and.callThrough();
    component.getUserById();
  
    expect(spyOnMethod).toHaveBeenCalled();
  });
});
