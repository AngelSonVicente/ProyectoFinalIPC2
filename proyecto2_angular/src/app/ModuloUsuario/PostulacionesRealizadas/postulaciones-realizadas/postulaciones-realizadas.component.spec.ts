import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostulacionesRealizadasComponent } from './postulaciones-realizadas.component';

describe('PostulacionesRealizadasComponent', () => {
  let component: PostulacionesRealizadasComponent;
  let fixture: ComponentFixture<PostulacionesRealizadasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostulacionesRealizadasComponent]
    });
    fixture = TestBed.createComponent(PostulacionesRealizadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
