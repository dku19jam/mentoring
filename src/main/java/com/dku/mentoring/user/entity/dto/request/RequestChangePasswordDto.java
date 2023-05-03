package com.dku.mentoring.user.entity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RequestChangePasswordDto {

    @NotBlank
    @Schema(description = "현재 비밀번호", example = "12345678")
    private final String presentPassword;

    @NotBlank
    @Schema(description = "새로운 비밀번호", example = "12345678")
    private final String newPassword;

    @NotBlank
    @Schema(description = "새로운 비밀번호 확인", example = "12345678")
    private final String newPasswordConfirm;
}
