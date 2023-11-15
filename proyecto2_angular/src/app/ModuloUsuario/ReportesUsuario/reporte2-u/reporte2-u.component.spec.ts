import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reporte2UComponent } from './reporte2-u.component';

describe('Reporte2UComponent', () => {
  let component: Reporte2UComponent;
  let fixture: ComponentFixture<Reporte2UComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Reporte2UComponent]
    });
    fixture = TestBed.createComponent(Reporte2UComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
