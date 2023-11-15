import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reporte4UComponent } from './reporte4-u.component';

describe('Reporte4UComponent', () => {
  let component: Reporte4UComponent;
  let fixture: ComponentFixture<Reporte4UComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Reporte4UComponent]
    });
    fixture = TestBed.createComponent(Reporte4UComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
