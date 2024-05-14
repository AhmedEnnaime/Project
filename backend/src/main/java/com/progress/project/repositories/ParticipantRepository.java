package com.progress.project.repositories;

import com.progress.project.models.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByBic(String bic);
}
