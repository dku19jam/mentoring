package com.dku.mentoring.user.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonPropertyOrder({"studentId", "password"})
public class RequestLoginDto {

    @NotBlank
    @Schema(description = "아이디(학번)", example = "1234567")
    private final String studentId;

    @NotBlank
    @Schema(description = "비밀번호", example = "123")
    private final String password;
}
