import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalizarEntrevistaComponent } from './finalizar-entrevista.component';

describe('FinalizarEntrevistaComponent', () => {
  let component: FinalizarEntrevistaComponent;
  let fixture: ComponentFixture<FinalizarEntrevistaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FinalizarEntrevistaComponent]
    });
    fixture = TestBed.createComponent(FinalizarEntrevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
