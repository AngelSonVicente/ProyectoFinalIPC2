import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarOfertaComponent } from './eliminar-oferta.component';

describe('EliminarOfertaComponent', () => {
  let component: EliminarOfertaComponent;
  let fixture: ComponentFixture<EliminarOfertaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EliminarOfertaComponent]
    });
    fixture = TestBed.createComponent(EliminarOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
