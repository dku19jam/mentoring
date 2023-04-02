package com.dku.mentoring.register.model.dto.list;

import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import lombok.Getter;


@Getter
public class SummarizedRegisterDto {
    private final Long id;

    private final String title;

    private final String body;

    private final String userName;

    private final String teamName;

    private final String missionDescription;

    private final RegisterStatus status;


    public SummarizedRegisterDto(Register register) {
        this.id = register.getId();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.userName = register.getUser().getName();
        this.teamName = register.getUser().getTeam().getTeamName();
        this.missionDescription = register.getMission().getDescription();
        this.status = register.getStatus();
    }
}
