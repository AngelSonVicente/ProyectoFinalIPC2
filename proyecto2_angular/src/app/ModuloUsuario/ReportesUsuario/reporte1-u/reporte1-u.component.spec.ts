import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reporte1UComponent } from './reporte1-u.component';

describe('Reporte1UComponent', () => {
  let component: Reporte1UComponent;
  let fixture: ComponentFixture<Reporte1UComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Reporte1UComponent]
    });
    fixture = TestBed.createComponent(Reporte1UComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
