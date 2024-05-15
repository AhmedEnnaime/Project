import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import IParticipant from 'src/app/core/interfaces/IParticipant';
import { ParticipantModalComponent } from '../participant-modal/participant-modal.component';

@Component({
  selector: 'app-participant-card',
  templateUrl: './participant-card.component.html',
  styleUrls: ['./participant-card.component.css'],
})
export class ParticipantCardComponent {
  @Input() participant?: IParticipant;

  constructor(public dialog: MatDialog) {}

  openUpdateDialog() {
    this.dialog.open(ParticipantModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
      data: { participant: this.participant },
    });
  }
}
