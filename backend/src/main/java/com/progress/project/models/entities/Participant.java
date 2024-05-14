package com.progress.project.models.entities;

import com.progress.project.models.enums.TYPE;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "participants")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Participant {

    @Id
    private String code;

    @Column(nullable = false, unique = true)
    private String bic;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private TYPE type;

    @Column(nullable = true)
    private String logo;

    @Column(nullable = true)
    private String settlementBank;

}
