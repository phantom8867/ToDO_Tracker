import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdittodolistComponent } from './edittodolist.component';

describe('EdittodolistComponent', () => {
  let component: EdittodolistComponent;
  let fixture: ComponentFixture<EdittodolistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdittodolistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdittodolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
