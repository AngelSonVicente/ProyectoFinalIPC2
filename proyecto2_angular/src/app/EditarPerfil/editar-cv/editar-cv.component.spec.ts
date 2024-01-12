import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarCVComponent } from './editar-cv.component';

describe('EditarCVComponent', () => {
  let component: EditarCVComponent;
  let fixture: ComponentFixture<EditarCVComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarCVComponent]
    });
    fixture = TestBed.createComponent(EditarCVComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
