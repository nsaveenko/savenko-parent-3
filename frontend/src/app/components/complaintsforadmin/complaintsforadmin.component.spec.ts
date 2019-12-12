import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintsforadminComponent } from './complaintsforadmin.component';

describe('ComplaintsforadminComponent', () => {
  let component: ComplaintsforadminComponent;
  let fixture: ComponentFixture<ComplaintsforadminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComplaintsforadminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintsforadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
