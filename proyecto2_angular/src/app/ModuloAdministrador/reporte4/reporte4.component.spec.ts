import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reporte4Component } from './reporte4.component';

describe('Reporte4Component', () => {
  let component: Reporte4Component;
  let fixture: ComponentFixture<Reporte4Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [Reporte4Component]
    });
    fixture = TestBed.createComponent(Reporte4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
