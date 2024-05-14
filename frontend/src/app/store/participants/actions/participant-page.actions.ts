import { createAction, props } from '@ngrx/store';
import IParticipant from 'src/app/core/interfaces/IParticipant';

export const enter = createAction('[Participant page] enter');

export const selectParticipant = createAction(
  '[Participant page] select news',
  props<{ participantCode: number }>()
);

export const unselectParticipant = createAction('[News page] unselect news');

export const addParticipant = createAction(
  '[Participant page] add Participant',
  props<{ participant: IParticipant }>()
);

export const updateParticipant = createAction(
  '[Participant page] update News',
  props<{ participant: IParticipant; participantCode: number }>()
);
