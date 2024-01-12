import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarPerfilGeneralComponent } from './editar-perfil-general.component';

describe('EditarPerfilGeneralComponent', () => {
  let component: EditarPerfilGeneralComponent;
  let fixture: ComponentFixture<EditarPerfilGeneralComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarPerfilGeneralComponent]
    });
    fixture = TestBed.createComponent(EditarPerfilGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
