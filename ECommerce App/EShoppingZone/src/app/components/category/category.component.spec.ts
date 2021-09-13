import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { CategoryComponent } from './category.component';
import { ProductService } from 'src/app/Services/product.service';

describe('CategoryComponent', () => {
  let component: CategoryComponent;
  let fixture: ComponentFixture<CategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ CategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should do getProductListByCategory method call', () => {
    const service = TestBed.get(ProductService);
    const spyOnMethod = spyOn(service,'getProductListByCategory').and.callThrough();
    component.getProductsByCategory();
  
    expect(spyOnMethod).toHaveBeenCalled();
  });
});
