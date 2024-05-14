import { createFeatureSelector, createSelector } from '@ngrx/store';
import { ParticipantState } from './participant.reducer';
import { PARTICIPANT_FEATURE_KEY } from './participant.state.module';

export const selectParticipantState = createFeatureSelector<ParticipantState>(
  PARTICIPANT_FEATURE_KEY
);

const getAllParticipants = (state: ParticipantState) => state.collection;
const getSelectedParticipantCode = (state: ParticipantState) =>
  state.selectedParticipantCode;
const getErrors = (state: ParticipantState) => state.errors;
const getLoadingState = (state: ParticipantState) => state.loading;

const getSelectedParticipant = createSelector(
  getAllParticipants,
  getSelectedParticipantCode,
  (participants, selectedParticipantCode) =>
    participants.find(
      (participant) => participant.code === selectedParticipantCode
    ) ?? null
);

export const selectParticipants = createSelector(
  selectParticipantState,
  getAllParticipants
);

export const selectSelectedParticipantCode = createSelector(
  selectParticipantState,
  getSelectedParticipantCode
);

export const selectSelectedParticipant = createSelector(
  selectParticipantState,
  getSelectedParticipant
);

export const selectLoadingState = createSelector(
  selectParticipantState,
  getLoadingState
);

export const selectErrorState = createSelector(
  selectParticipantState,
  getErrors
);
