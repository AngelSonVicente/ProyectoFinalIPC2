import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionPostulacionesComponent } from './revision-postulaciones.component';

describe('RevisionPostulacionesComponent', () => {
  let component: RevisionPostulacionesComponent;
  let fixture: ComponentFixture<RevisionPostulacionesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RevisionPostulacionesComponent]
    });
    fixture = TestBed.createComponent(RevisionPostulacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
