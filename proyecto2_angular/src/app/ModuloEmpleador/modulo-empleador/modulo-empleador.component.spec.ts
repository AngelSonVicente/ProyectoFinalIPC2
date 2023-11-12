import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloEmpleadorComponent } from './modulo-empleador.component';

describe('ModuloEmpleadorComponent', () => {
  let component: ModuloEmpleadorComponent;
  let fixture: ComponentFixture<ModuloEmpleadorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModuloEmpleadorComponent]
    });
    fixture = TestBed.createComponent(ModuloEmpleadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
