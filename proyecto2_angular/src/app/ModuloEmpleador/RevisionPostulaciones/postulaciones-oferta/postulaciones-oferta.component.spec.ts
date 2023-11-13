import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostulacionesOfertaComponent } from './postulaciones-oferta.component';

describe('PostulacionesOfertaComponent', () => {
  let component: PostulacionesOfertaComponent;
  let fixture: ComponentFixture<PostulacionesOfertaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostulacionesOfertaComponent]
    });
    fixture = TestBed.createComponent(PostulacionesOfertaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
