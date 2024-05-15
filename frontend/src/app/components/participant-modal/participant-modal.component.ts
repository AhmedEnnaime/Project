import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Store } from '@ngrx/store';
import TYPE from 'src/app/core/enums/TYPE';
import IParticipant from 'src/app/core/interfaces/IParticipant';
import * as participantPageActions from '../../store/participants/actions/participant-page.actions';

@Component({
  selector: 'app-participant-modal',
  templateUrl: './participant-modal.component.html',
  styleUrls: ['./participant-modal.component.css'],
})
export class ParticipantModalComponent {
  typeEnum = TYPE;
  form: FormGroup;
  constructor(
    private dialogRef: MatDialogRef<ParticipantModalComponent>,
    private store: Store,
    @Inject(MAT_DIALOG_DATA) public data: { participant: IParticipant }
  ) {
    this.form = new FormGroup({
      code: new FormControl<string>({ value: '', disabled: true }),
      bic: new FormControl<string>(''),
      name: new FormControl<string>(''),
      shortName: new FormControl<string>(''),
      type: new FormControl<string>(''),
      logo: new FormControl<string>(''),
      settlementBank: new FormControl<string>({ value: '', disabled: true }),
    });
  }

  ngOnInit(): void {
    if (this.data.participant !== undefined) {
      this.form.patchValue({
        code: this.data.participant.code ?? '',
        bic: this.data.participant.bic || '',
        name: this.data.participant.name || '',
        shortName: this.data.participant.shortName || '',
        type: this.data.participant.type || '',
        logo: this.data.participant.logo || '',
        settlementBank: this.data.participant.settlementBank || '',
      });
      this.form.get('code')?.disable();
    } else {
      this.form.get('code')?.enable();
    }

    this.form.get('type')?.valueChanges.subscribe((value) => {
      const settlementBankControl = this.form.get('settlementBank');
      if (value === this.typeEnum.INDIRECT) {
        settlementBankControl?.enable();
      } else {
        settlementBankControl?.disable();
      }
    });
  }

  handleParticipant() {
    this.form.get('code')?.enable();
    const participant: IParticipant = {
      code: this.form.value.code ?? '',
      bic: this.form.value.bic ?? '',
      name: this.form.value.name ?? '',
      shortName: this.form.value.shortName ?? '',
      type: this.form.value.type ?? '',
      logo: this.form.value.logo ?? '',
      settlementBank: this.form.value.settlementBank ?? '',
    };

    if (this.data.participant !== undefined) {
      const participantCode: string = this.data.participant.code ?? '';
      this.store.dispatch(
        participantPageActions.updateParticipant({
          participant,
          participantCode,
        })
      );
    } else {
      this.store.dispatch(
        participantPageActions.addParticipant({ participant })
      );
    }
    this.form.get('code')?.disable();

    this.dialogRef.close();
  }
}
