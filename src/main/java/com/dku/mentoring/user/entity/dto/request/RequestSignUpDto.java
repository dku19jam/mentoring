package com.dku.mentoring.user.entity.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RequestSignUpDto {

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(description = "학번", example = "12345678")
    private final String studentId;

    @NotBlank
    @Size(min = 2, max = 20)
    @Schema(description = "이름", example = "홍길동")
    private final String name;

    @Schema(description = "팀 이름", example = "팀1")
    private final String teamName;

    @Schema(description = "멘티 이름", example = "멘티1, 멘티2, 멘티3")
    private final String menteeName;

    @NotBlank
    @Size(min = 6, max = 200)
    @Schema(description = "비밀번호", example = "123456")
    private final String password;
}
