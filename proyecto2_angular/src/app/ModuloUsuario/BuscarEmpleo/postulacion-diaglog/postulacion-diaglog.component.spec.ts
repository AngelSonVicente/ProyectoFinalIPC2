import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostulacionDiaglogComponent } from './postulacion-diaglog.component';

describe('PostulacionDiaglogComponent', () => {
  let component: PostulacionDiaglogComponent;
  let fixture: ComponentFixture<PostulacionDiaglogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostulacionDiaglogComponent]
    });
    fixture = TestBed.createComponent(PostulacionDiaglogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
