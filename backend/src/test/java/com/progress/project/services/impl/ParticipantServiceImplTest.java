package com.progress.project.services.impl;

import com.progress.project.exceptions.BicAlreadyExistsException;
import com.progress.project.exceptions.CodeAlreadyExistsException;
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
                .code(123456L)
                .bic("abcd")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("sample bank")
                .build();

        participantDto = ParticipantDto.builder()
                .code(123456L)
                .bic("abcd")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("sample bank")
                .build();
    }

    @Test
    @DisplayName("Test create method when the code already exists")
    public void testCreateWhenCodeAlreadyExists() {
        given(participantRepository.existsById(123456L)).willReturn(true);
        assertThatExceptionOfType(CodeAlreadyExistsException.class)
                .isThrownBy(() -> participantService.create(participantDto))
                .withMessage("This Code " + participantDto.getCode() + " already exists");
    }

    @Test
    @DisplayName("Test create method when the bic already exists")
    public void testCreateWhenBicAlreadyExists() {
        given(participantRepository.existsById(123456L)).willReturn(false);
        given(participantRepository.findByBic("abcd")).willReturn(Optional.of(participant));
        assertThatExceptionOfType(BicAlreadyExistsException.class)
                .isThrownBy(() -> participantService.create(participantDto))
                .withMessage("This Bic " + participantDto.getBic() + " already exists");
    }
    
}
