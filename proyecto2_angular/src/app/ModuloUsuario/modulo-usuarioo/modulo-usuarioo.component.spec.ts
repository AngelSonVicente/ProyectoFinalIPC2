import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloUsuariooComponent } from './modulo-usuarioo.component';

describe('ModuloUsuariooComponent', () => {
  let component: ModuloUsuariooComponent;
  let fixture: ComponentFixture<ModuloUsuariooComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModuloUsuariooComponent]
    });
    fixture = TestBed.createComponent(ModuloUsuariooComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
