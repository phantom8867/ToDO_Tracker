import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfullyregisterationComponent } from './successfullyregisteration.component';

describe('SuccessfullyregisterationComponent', () => {
  let component: SuccessfullyregisterationComponent;
  let fixture: ComponentFixture<SuccessfullyregisterationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessfullyregisterationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessfullyregisterationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
