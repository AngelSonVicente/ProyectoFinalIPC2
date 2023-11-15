import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargaJsonComponent } from './carga-json.component';

describe('CargaJsonComponent', () => {
  let component: CargaJsonComponent;
  let fixture: ComponentFixture<CargaJsonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CargaJsonComponent]
    });
    fixture = TestBed.createComponent(CargaJsonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
