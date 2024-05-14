import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantModalComponent } from './participant-modal.component';

describe('ParticipantModalComponent', () => {
  let component: ParticipantModalComponent;
  let fixture: ComponentFixture<ParticipantModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ParticipantModalComponent]
    });
    fixture = TestBed.createComponent(ParticipantModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
