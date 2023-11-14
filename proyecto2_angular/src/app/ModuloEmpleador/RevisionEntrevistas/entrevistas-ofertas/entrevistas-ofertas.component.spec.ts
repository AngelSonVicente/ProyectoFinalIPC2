import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrevistasOfertasComponent } from './entrevistas-ofertas.component';

describe('EntrevistasOfertasComponent', () => {
  let component: EntrevistasOfertasComponent;
  let fixture: ComponentFixture<EntrevistasOfertasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntrevistasOfertasComponent]
    });
    fixture = TestBed.createComponent(EntrevistasOfertasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
