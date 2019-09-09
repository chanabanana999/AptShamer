import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AptComplaintsComponent } from './apt-complaints.component';

describe('AptComplaintsComponent', () => {
  let component: AptComplaintsComponent;
  let fixture: ComponentFixture<AptComplaintsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AptComplaintsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AptComplaintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
