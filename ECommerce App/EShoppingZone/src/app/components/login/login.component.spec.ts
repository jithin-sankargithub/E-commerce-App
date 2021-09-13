import { ComponentFixture, inject, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { LoginComponent } from './login.component';
import { FormBuilder } from '@angular/forms';
import { AuthService } from 'src/app/Services/auth.service';

class MockAuthService extends AuthService{
  public isLoggedIn(){
    return true;
  }
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let testBedAuthService : AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[ HttpClientTestingModule,
        RouterTestingModule],
      declarations: [ LoginComponent ],
      providers: [FormBuilder]
    })
    .compileComponents();
    TestBed.overrideComponent(
      LoginComponent,
      {set : {providers : [{provide : AuthService,useClass : MockAuthService}]}}
    )
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    testBedAuthService = TestBed.get(AuthService);
  });

 
  it('should check the service', () => {
    expect(testBedAuthService instanceof AuthService).toBeTruthy();
  });
  it('should inject service using inject funtion and check its instance', inject([AuthService],(injectedService: AuthService) =>{
    expect(injectedService).toBeTruthy();
    expect(injectedService instanceof AuthService).toBeTruthy();
  }));

  it('should test injected service injected using component overriding', ()=>{
    let overRiddenService = fixture.debugElement.injector.get(AuthService);
    expect(overRiddenService instanceof MockAuthService).toBeTruthy();
  })
  
});
