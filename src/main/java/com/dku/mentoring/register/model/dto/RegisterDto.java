package com.dku.mentoring.register.model.dto;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterDto {

    //게시글 아이디
    private final Long id;

    private final User user;

    private final String title;

    private final String body;

    private final LocalDateTime createdAt;

    private final LocalDateTime lastModifiedAt;

    private final Mission mission;

    private final List<RegisterFileDto> files;

    public RegisterDto(String baseImageUrl, Register register){
        this.id = register.getId();
        this.user = register.getUser();
        this.title = register.getTitle();
        this.body = register.getBody();
        this.createdAt = register.getCreatedAt();
        this.mission = register.getMission();
        this.lastModifiedAt = register.getLastModifiedAt();
        this.files = RegisterFileDto.listOf(baseImageUrl, register.getFiles());
    }
}
