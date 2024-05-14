package com.progress.project.models.dto;


import com.progress.project.models.enums.TYPE;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ParticipantDto {

    @NotNull(message = "code must not be null")
    @Min(value = 100000, message = "code must be a 6-digit number")
    @Max(value = 999999, message = "code must be a 6-digit number")
    private Long code;

    @NotNull(message = "bic must not be null")
    @NotEmpty(message = "bic must not be empty")
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
