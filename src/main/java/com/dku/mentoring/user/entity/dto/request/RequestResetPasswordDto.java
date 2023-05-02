package com.dku.mentoring.user.entity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)

public class RequestResetPasswordDto {

    @NotBlank
    @Schema(description = "학번", example = "12345678")
    private final String studentId;
}
