package com.dku.mentoring.register.model.dto.response;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterResponseDto {

    private Long id;

    private String title;

    private String body;

    private Long missionId;

    private RegisterStatus status;

    @Builder
    public RegisterResponseDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.missionId = register.getMission().getId();
        this.status = register.getStatus();
    }

}
