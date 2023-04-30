import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowfriendsComponent } from './showfriends.component';

describe('ShowfriendsComponent', () => {
  let component: ShowfriendsComponent;
  let fixture: ComponentFixture<ShowfriendsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowfriendsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowfriendsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
