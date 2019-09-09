import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplexDetailComponent } from './complex-detail.component';

describe('ComplexDetailComponent', () => {
  let component: ComplexDetailComponent;
  let fixture: ComponentFixture<ComplexDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComplexDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplexDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
