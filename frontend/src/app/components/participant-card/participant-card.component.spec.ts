import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantCardComponent } from './participant-card.component';

describe('ParticipantCardComponent', () => {
  let component: ParticipantCardComponent;
  let fixture: ComponentFixture<ParticipantCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ParticipantCardComponent]
    });
    fixture = TestBed.createComponent(ParticipantCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
