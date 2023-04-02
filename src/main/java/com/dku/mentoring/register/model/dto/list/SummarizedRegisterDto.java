package com.dku.mentoring.register.model.dto.list;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SummarizedRegisterDto {
    private final Long id;

    private final String title;

    private final String body;

    private final String teamName;

    private final String description;

    private final RegisterStatus status;

    private final LocalDateTime createdAt;

    private final LocalDateTime lastModifiedAt;

    public SummarizedRegisterDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.teamName = register.getUser().getTeam().getTeamName();
        this.description = register.getMission().getDescription();
        this.status = register.getStatus();
        this.createdAt = register.getCreatedAt();
        this.lastModifiedAt = register.getLastModifiedAt();
    }
}
