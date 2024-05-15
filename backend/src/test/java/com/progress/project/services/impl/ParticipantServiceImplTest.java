package com.progress.project.services.impl;

import com.progress.project.exceptions.BicAlreadyExistsException;
import com.progress.project.exceptions.BicNotValidException;
import com.progress.project.exceptions.CodeAlreadyExistsException;
import com.progress.project.exceptions.ResourceNotFoundException;
import com.progress.project.models.dto.ParticipantDto;
import com.progress.project.models.entities.Participant;
import com.progress.project.models.enums.TYPE;
import com.progress.project.repositories.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class ParticipantServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantServiceImpl participantService;

    @Mock
    private ModelMapper modelMapper;

    private Participant participant;

    private ParticipantDto participantDto;

    @BeforeEach
    public void setUp() {
        participant = Participant.builder()
                .code("123456")
                .bic("ABCD-XY-12-123")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("123456")
                .build();

        participantDto = ParticipantDto.builder()
                .code("123456")
                .bic("ABCD-XY-12-123")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("123456")
                .build();
    }

    @Test
    @DisplayName("Test create method when the code already exists")
    public void testCreateWhenCodeAlreadyExists() {
        given(participantRepository.existsById("123456")).willReturn(true);
        assertThatExceptionOfType(CodeAlreadyExistsException.class)
                .isThrownBy(() -> participantService.create(participantDto))
                .withMessage("This Code " + participantDto.getCode() + " already exists");
    }

    @Test
    @DisplayName("Test create method when the bic already exists")
    public void testCreateWhenBicAlreadyExists() {
        given(participantRepository.existsById("123456")).willReturn(false);
        given(participantRepository.findByBic("ABCD-XY-12-123")).willReturn(Optional.of(participant));
        assertThatExceptionOfType(BicAlreadyExistsException.class)
                .isThrownBy(() -> participantService.create(participantDto))
                .withMessage("This Bic " + participantDto.getBic() + " already exists");
    }

    @Test
    @DisplayName("Test create method when the bic already exists")
    public void testCreateWhenBicNotValid() {
        participantDto = ParticipantDto.builder()
                .code("123456")
                .bic("ABCD-XY-12-123")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("xhwi")
                .build();
        
        given(participantRepository.existsById("123456")).willReturn(false);
        given(participantRepository.findByBic("ABCD-XY-12-123")).willReturn(Optional.empty());
        assertThatExceptionOfType(BicNotValidException.class)
                .isThrownBy(() -> participantService.create(participantDto))
                .withMessage("settlementBank must be a 6-digit number");
    }

    @Test
    @DisplayName("Test create method when the insertion is successful")
    public void testCreateMethodSuccess() {
        given(modelMapper.map(participantDto, Participant.class)).willReturn(participant);
        given(participantRepository.existsById("123456")).willReturn(false);
        given(participantRepository.findByBic("ABCD-XY-12-123")).willReturn(Optional.empty());
        given(participantRepository.save(participant)).willReturn(participant);
        given(modelMapper.map(participant, ParticipantDto.class)).willReturn(participantDto);
        ParticipantDto result = participantService.create(participantDto);
        assertThat(result).isEqualTo(participantDto);
        verify(participantRepository, times(1)).existsById("123456");
        verify(participantRepository, times(1)).findByBic("ABCD-XY-12-123");
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    @DisplayName("Test findAll method when the list is empty")
    public void testFindAllWhenListIsEmpty() {
        given(participantRepository.findAll()).willReturn(Collections.emptyList());
        List<ParticipantDto> allParticipants = participantService.findAll();
        assertThat(allParticipants).isEmpty();
    }

    @Test
    @DisplayName("Test findAll method when the list is not empty")
    public void testFindAllWhenListIsNotEmpty() {
        given(participantRepository.findAll()).willReturn(List.of(participant));
        given(modelMapper.map(participant, ParticipantDto.class)).willReturn(participantDto);
        List<ParticipantDto> allParticipants = participantService.findAll();
        verify(participantRepository, times(1)).findAll();
        assertThat(allParticipants)
                .isNotNull()
                .hasSize(1);
    }

    @Test
    @DisplayName("Test update method when the code of participant not found")
    public void testUpdateWhenCodeNotFound() {
        given(participantRepository.findById(participantDto.getCode())).willReturn(Optional.empty());
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> participantService.update(participantDto.getCode() ,participantDto))
                .withMessage("Participant with code " + participantDto.getCode() + " not found");
    }

    @Test
    @DisplayName("Test update method when the code of participant is found")
    public void testUpdateWhenCodeIsFound() {
        given(participantRepository.findById(participantDto.getCode())).willReturn(Optional.of(participant));
        given(participantRepository.save(participant)).willReturn(participant);
        given(modelMapper.map(participant, ParticipantDto.class)).willReturn(participantDto);
        ParticipantDto result = participantService.update(participantDto.getCode(), participantDto);
        assertThat(result).isEqualTo(participantDto);
        verify(participantRepository, times(1)).findById(participantDto.getCode());
        verify(participantRepository, times(1)).save(participant);
    }


}
