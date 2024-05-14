package com.progress.project.services.impl;

import com.progress.project.exceptions.CodeAlreadyExistsException;
import com.progress.project.models.dto.ParticipantDto;
import com.progress.project.models.entities.Participant;
import com.progress.project.models.enums.TYPE;
import com.progress.project.repositories.ParticipantRepository;
import com.progress.project.services.ParticipantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ModelMapper modelMapper;
    private final ParticipantRepository participantRepository;

    @Override
    public ParticipantDto create(ParticipantDto participantDto) {
        Participant participant = modelMapper.map(participantDto, Participant.class);
        if (participantRepository.existsById(participantDto.getCode()))
            throw new CodeAlreadyExistsException("This Code " + participantDto.getCode() + " already exists");
        if (participantDto.getType() == TYPE.INDIRECT) {
            participant.setSettlementBank(participantDto.getSettlementBank());
        }else {
            participant.setSettlementBank(null);
        }
        return modelMapper.map(participantRepository.save(participant), ParticipantDto.class);
    }

    @Override
    public ParticipantDto update(String code, ParticipantDto participantDto) {
        return null;
    }

    @Override
    public List<ParticipantDto> findAll() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .toList();
    }
}
