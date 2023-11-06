import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarOfertasEmpleoComponent } from './buscar-ofertas-empleo.component';

describe('BuscarOfertasEmpleoComponent', () => {
  let component: BuscarOfertasEmpleoComponent;
  let fixture: ComponentFixture<BuscarOfertasEmpleoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuscarOfertasEmpleoComponent]
    });
    fixture = TestBed.createComponent(BuscarOfertasEmpleoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
