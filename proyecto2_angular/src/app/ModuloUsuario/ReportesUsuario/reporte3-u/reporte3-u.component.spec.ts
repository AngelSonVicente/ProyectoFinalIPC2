import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reporte3UComponent } from './reporte3-u.component';

describe('Reporte3UComponent', () => {
  let component: Reporte3UComponent;
  let fixture: ComponentFixture<Reporte3UComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Reporte3UComponent]
    });
    fixture = TestBed.createComponent(Reporte3UComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
