import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesOfertasComponent } from './detalles-ofertas.component';

describe('DetallesOfertasComponent', () => {
  let component: DetallesOfertasComponent;
  let fixture: ComponentFixture<DetallesOfertasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetallesOfertasComponent]
    });
    fixture = TestBed.createComponent(DetallesOfertasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
