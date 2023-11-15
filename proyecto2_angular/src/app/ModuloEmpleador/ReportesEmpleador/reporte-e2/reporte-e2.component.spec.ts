import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteE2Component } from './reporte-e2.component';

describe('ReporteE2Component', () => {
  let component: ReporteE2Component;
  let fixture: ComponentFixture<ReporteE2Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReporteE2Component]
    });
    fixture = TestBed.createComponent(ReporteE2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
