import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodofrontpageComponent } from './todofrontpage.component';

describe('TodofrontpageComponent', () => {
  let component: TodofrontpageComponent;
  let fixture: ComponentFixture<TodofrontpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodofrontpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodofrontpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
