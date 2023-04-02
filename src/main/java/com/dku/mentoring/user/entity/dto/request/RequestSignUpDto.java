package com.dku.mentoring.user.entity.dto.request;

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
    private final String studentId;

    @NotBlank
    @Size(min = 2, max = 20)
    private final String name;

    private final String teamName;

    @NotBlank
    @Size(min = 3, max = 200)
    private final String password;
}
