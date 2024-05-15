package com.progress.project.models.dto;


import com.progress.project.models.enums.TYPE;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ParticipantDto {

    @NotNull(message = "code must not be null")
    @Pattern(regexp = "^[0-9]{6}$", message = "code must be a 6-digit number")
    private String code;

    @NotNull(message = "bic must not be null")
    @NotEmpty(message = "bic must not be empty")
    @Pattern(regexp = "^[A-Z]{4}[-]?[A-Z]{2}[-]?[A-Z0-9]{2}[-]?[0-9]{3}$", message = "bic must be valid")
    private String bic;

    @NotNull(message = "name must not be null")
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotNull(message = "bic must not be null")
    @NotEmpty(message = "bic must not be empty")
    @Size(min = 2, max = 3, message = "shortName must be between 2 and 3 characters")
    @Pattern(regexp = "^[A-Z]*$", message = "shortName must contain only uppercase letters")
    private String shortName;

    @NotNull(message = "bic must not be null")
    private TYPE type;

    private String logo;
    
    private String settlementBank;

}
