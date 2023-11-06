import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CambiarComisionComponent } from './cambiar-comision.component';

describe('CambiarComisionComponent', () => {
  let component: CambiarComisionComponent;
  let fixture: ComponentFixture<CambiarComisionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CambiarComisionComponent]
    });
    fixture = TestBed.createComponent(CambiarComisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
