import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { ParticipantReducer } from './participant.reducer';
import { ParticipantEffect } from './participant.effect';

export const PARTICIPANT_FEATURE_KEY = 'PARTICIPANT';

@NgModule({
  imports: [
    StoreModule.forFeature(PARTICIPANT_FEATURE_KEY, ParticipantReducer),
    EffectsModule.forFeature([ParticipantEffect]),
  ],
})
export class ParticipantStateModule {}
