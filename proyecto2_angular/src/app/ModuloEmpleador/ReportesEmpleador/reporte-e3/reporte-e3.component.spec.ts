import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteE3Component } from './reporte-e3.component';

describe('ReporteE3Component', () => {
  let component: ReporteE3Component;
  let fixture: ComponentFixture<ReporteE3Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReporteE3Component]
    });
    fixture = TestBed.createComponent(ReporteE3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
