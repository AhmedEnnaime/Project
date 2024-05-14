package com.progress.project.services;

import com.progress.project.models.dto.ParticipantDto;

import java.util.List;

public interface ParticipantService {

    ParticipantDto create(ParticipantDto participantDto);
    ParticipantDto update(String code, ParticipantDto participantDto);
    List<ParticipantDto> findAll();
}
