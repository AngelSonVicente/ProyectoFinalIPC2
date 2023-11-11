import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrevistasPendientesComponent } from './entrevistas-pendientes.component';

describe('EntrevistasPendientesComponent', () => {
  let component: EntrevistasPendientesComponent;
  let fixture: ComponentFixture<EntrevistasPendientesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntrevistasPendientesComponent]
    });
    fixture = TestBed.createComponent(EntrevistasPendientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
