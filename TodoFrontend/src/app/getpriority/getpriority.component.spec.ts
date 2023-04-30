import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetpriorityComponent } from './getpriority.component';

describe('GetpriorityComponent', () => {
  let component: GetpriorityComponent;
  let fixture: ComponentFixture<GetpriorityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetpriorityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetpriorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
