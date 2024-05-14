package com.progress.project.services.impl;

import com.progress.project.exceptions.BicAlreadyExistsException;
import com.progress.project.exceptions.CodeAlreadyExistsException;
import com.progress.project.exceptions.ResourceNotFoundException;
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
        if (participantRepository.findByBic(participantDto.getBic()).isPresent())
            throw new BicAlreadyExistsException("This Bic " + participantDto.getBic() + " already exists");
        if (participantDto.getType() == TYPE.INDIRECT) {
            participant.setSettlementBank(participantDto.getSettlementBank());
        }else {
            participant.setSettlementBank(null);
        }
        return modelMapper.map(participantRepository.save(participant), ParticipantDto.class);
    }

    @Override
    public ParticipantDto update(Long code, ParticipantDto participantDto) {
       
        Participant existingParticipant = participantRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Participant with code " + code + " not found"));

        existingParticipant.setName(participantDto.getName());
        existingParticipant.setBic(participantDto.getBic());
        existingParticipant.setShortName(participantDto.getShortName());
        existingParticipant.setLogo(participantDto.getLogo());
        existingParticipant.setType(participantDto.getType());
        existingParticipant.setCode(participantDto.getCode());

        if (participantDto.getType() == TYPE.INDIRECT) {
            existingParticipant.setSettlementBank(participantDto.getSettlementBank());
        } else {
            existingParticipant.setSettlementBank(null);
        }

        Participant updatedParticipant = participantRepository.save(existingParticipant);
        return modelMapper.map(updatedParticipant, ParticipantDto.class);
    }


    @Override
    public List<ParticipantDto> findAll() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .toList();
    }
}
