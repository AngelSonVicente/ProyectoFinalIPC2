import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarTelefonoCategoriaComponent } from './editar-telefono-categoria.component';

describe('EditarTelefonoCategoriaComponent', () => {
  let component: EditarTelefonoCategoriaComponent;
  let fixture: ComponentFixture<EditarTelefonoCategoriaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarTelefonoCategoriaComponent]
    });
    fixture = TestBed.createComponent(EditarTelefonoCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
