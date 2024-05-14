import { createAction, props } from '@ngrx/store';
import IParticipant from 'src/app/core/interfaces/IParticipant';

export const participantsLoadedSuccessfully = createAction(
  '[Participant api] Participants loaded successfully',
  props<{ participants: IParticipant[] }>()
);

export const participantsLoadedFailure = createAction(
  '[Participant api] participants loaded failure',
  props<{ errors: {} }>()
);

export const participantAddedSuccessfully = createAction(
  '[Participant api] participant added successfully',
  props<{ addedParticipant: IParticipant }>()
);

export const participantAddedFailure = createAction(
  '[Participant api] participant added failure',
  props<{ errors: {} }>()
);

export const participantUpdatedSuccessfully = createAction(
  '[Participant api] participant updated successfully',
  props<{ updatedParticipant: IParticipant }>()
);

export const participantUpdatedFailure = createAction(
  '[Participant api] participant updated failure',
  props<{ errors: {} }>()
);
