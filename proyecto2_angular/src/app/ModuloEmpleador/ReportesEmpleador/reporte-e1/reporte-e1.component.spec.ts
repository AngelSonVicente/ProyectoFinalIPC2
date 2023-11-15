import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteE1Component } from './reporte-e1.component';

describe('ReporteE1Component', () => {
  let component: ReporteE1Component;
  let fixture: ComponentFixture<ReporteE1Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReporteE1Component]
    });
    fixture = TestBed.createComponent(ReporteE1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
