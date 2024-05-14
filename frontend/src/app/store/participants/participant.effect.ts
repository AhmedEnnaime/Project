import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as participantPageActions from './actions/participant-page.actions';
import * as participantApiActions from './actions/participant.api.actions';
import { concatMap, exhaustMap, map, mergeMap } from 'rxjs';
import { Injectable } from '@angular/core';
import { ParticipantService } from 'src/app/services/participant.service';

@Injectable()
export class ParticipantEffect {
  constructor(
    private actions$: Actions,
    private participantService: ParticipantService
  ) {}

  loadParticipants$ = createEffect(() =>
    this.actions$.pipe(
      ofType(participantPageActions.enter),
      exhaustMap(() =>
        this.participantService.getParticipants().pipe(
          map((participants) =>
            participantApiActions.participantsLoadedSuccessfully({
              participants,
            })
          )
        )
      )
    )
  );

  createParticipant$ = createEffect(() =>
    this.actions$.pipe(
      ofType(participantPageActions.addParticipant),
      concatMap((action) =>
        this.participantService.createParticipant(action.participant).pipe(
          map((addedParticipant) =>
            participantApiActions.participantAddedSuccessfully({
              addedParticipant,
            })
          )
        )
      )
    )
  );

  updateParticipant$ = createEffect(() =>
    this.actions$.pipe(
      ofType(participantPageActions.updateParticipant),
      concatMap((action) =>
        this.participantService
          .updateParticipant(action.participantCode, action.participant)
          .pipe(
            map((updatedParticipant) =>
              participantApiActions.participantUpdatedSuccessfully({
                updatedParticipant,
              })
            )
          )
      )
    )
  );
}
