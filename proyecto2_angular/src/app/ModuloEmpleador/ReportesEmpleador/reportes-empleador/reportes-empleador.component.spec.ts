import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportesEmpleadorComponent } from './reportes-empleador.component';

describe('ReportesEmpleadorComponent', () => {
  let component: ReportesEmpleadorComponent;
  let fixture: ComponentFixture<ReportesEmpleadorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportesEmpleadorComponent]
    });
    fixture = TestBed.createComponent(ReportesEmpleadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
