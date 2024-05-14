package com.progress.project.controllers;

import com.progress.project.models.dto.ParticipantDto;
import com.progress.project.services.ParticipantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/participants", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Slf4j
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    public ResponseEntity<ParticipantDto> createParticipant(@Valid @RequestBody ParticipantDto participantDto) {
        ParticipantDto createdParticipant = participantService.create(participantDto);
        log.info("Created Participant: {}", createdParticipant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ParticipantDto> createParticipant(@Valid @RequestBody ParticipantDto participantDto, @PathVariable Long code) {
        ParticipantDto updatedParticipant = participantService.update(code, participantDto);
        log.info("Updated Participant: {}", updatedParticipant);
        return ResponseEntity.ok(updatedParticipant);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getParticipants() {
        List<ParticipantDto> participants = participantService.findAll();
        return ResponseEntity.ok(participants);
    }
}
