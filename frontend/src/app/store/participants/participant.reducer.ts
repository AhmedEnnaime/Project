import { createReducer, on } from '@ngrx/store';
import * as participantPageActions from './actions/participant-page.actions';
import * as participantApiActions from './actions/participant.api.actions';
import IParticipant from 'src/app/core/interfaces/IParticipant';

export interface ParticipantState {
  collection: IParticipant[];
  selectedParticipantCode: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: ParticipantState = {
  collection: [],
  selectedParticipantCode: null,
  loading: false,
  errors: {},
};

export const ParticipantReducer = createReducer(
  initialState,
  on(participantPageActions.enter, (state, action) => ({
    ...state,
    selectedParticipantCode: null,
    loading: true,
  })),
  on(participantPageActions.selectParticipant, (state, action) => ({
    ...state,
    selectedParticipantCode: action.participantCode,
    loading: true,
  })),
  on(participantPageActions.unselectParticipant, (state, action) => ({
    ...state,
    selectedParticipantCode: null,
  })),
  on(participantApiActions.participantsLoadedSuccessfully, (state, action) => ({
    ...state,
    collection: action.participants,
  })),
  on(participantApiActions.participantAddedSuccessfully, (state, action) => ({
    collection: createParticipant(state.collection, action.addedParticipant),
    selectedParticipantCode: null,
    loading: false,
    errors: {},
  })),
  on(participantApiActions.participantUpdatedSuccessfully, (state, action) => ({
    collection: updateParticipant(state.collection, action.updatedParticipant),
    selectedParticipantCode: null,
    loading: false,
    errors: {},
  })),
  on(
    participantApiActions.participantsLoadedFailure,
    participantApiActions.participantAddedFailure,
    participantApiActions.participantUpdatedFailure,
    (state, action) => ({
      ...state,
      loading: false,
      errors: action.errors,
    })
  )
);

const createParticipant = (
  participants: IParticipant[],
  newParticipant: IParticipant
) => [...participants, newParticipant];

const updateParticipant = (
  participants: IParticipant[],
  updatedParticipant: IParticipant
) =>
  participants.map((participant) =>
    participant.code === updatedParticipant.code
      ? Object.assign({}, participant, updatedParticipant)
      : participant
  );
