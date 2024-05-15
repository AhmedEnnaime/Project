package com.progress.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progress.project.models.dto.ParticipantDto;
import com.progress.project.models.enums.TYPE;
import com.progress.project.services.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ParticipantControllerTest {

    private final String END_PONT = "/api/participants";
    private MockMvc mockMvc;
    private ParticipantService participantService;

    private ObjectMapper objectMapper;

    @Autowired
    public ParticipantControllerTest(MockMvc mockMvc, ParticipantService participantService) {
        this.mockMvc = mockMvc;
        this.participantService = participantService;
        objectMapper = new ObjectMapper();
    }

    @Test
    void createMethodSuccessfullyReturns201Created() throws Exception {
        var participantDto = ParticipantDto.builder()
                .code("123456")
                .bic("ABCD-XY-12-123")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.DIRECT)
                .settlementBank("123456")
                .build();

        var participantDtoJson = objectMapper.writeValueAsString(participantDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(END_PONT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(participantDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void createMethodSuccessfullyReturnsTheCreatedLevel() throws Exception {
        var participantDto = ParticipantDto.builder()
                .code("123456")
                .bic("ABCD-XY-12-123")
                .name("Ahmed")
                .shortName("AHM")
                .logo("logo")
                .type(TYPE.INDIRECT)
                .settlementBank("123456")
                .build();
        var participantDtoJson = objectMapper.writeValueAsString(participantDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(END_PONT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(participantDtoJson)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(participantDto.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(participantDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bic").value(participantDto.getBic()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortName").value(participantDto.getShortName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logo").value(participantDto.getLogo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.settlementBank").value(participantDto.getSettlementBank()));
    }

    @Test
    void findAllMethodReturns200Ok() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(END_PONT)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}
