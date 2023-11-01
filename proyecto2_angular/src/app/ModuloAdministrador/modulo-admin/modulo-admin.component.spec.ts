import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloAdminComponent } from './modulo-admin.component';

describe('ModuloAdminComponent', () => {
  let component: ModuloAdminComponent;
  let fixture: ComponentFixture<ModuloAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModuloAdminComponent]
    });
    fixture = TestBed.createComponent(ModuloAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
