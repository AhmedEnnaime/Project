import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import IParticipant from 'src/app/core/interfaces/IParticipant';
import { selectParticipants } from 'src/app/store/participants/participant.selector';
import { ParticipantModalComponent } from '../participant-modal/participant-modal.component';
import * as participantPageActions from '../../store/participants/actions/participant-page.actions';

@Component({
  selector: 'app-participants',
  templateUrl: './participants.component.html',
  styleUrls: ['./participants.component.css'],
})
export class ParticipantsComponent {
  participants: Observable<IParticipant[]>;

  constructor(private store: Store, public dialog: MatDialog) {
    this.participants = store.select(selectParticipants);
  }

  openDialog() {
    this.dialog.open(ParticipantModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
      data: { participant: undefined },
    });
  }

  ngOnInit(): void {
    this.store.dispatch(participantPageActions.enter());
  }
}
