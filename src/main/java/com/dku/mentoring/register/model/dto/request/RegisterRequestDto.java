package com.dku.mentoring.register.model.dto.request;

import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterStatus;
import com.dku.mentoring.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequestDto {

    @Schema(description = "제목", example = "제목")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String body;

    @Schema(description = "미션 ID", example = "1")
    private Long missionId;

    @Builder
    public RegisterRequestDto(String title, String body, Long missionId) {
        this.title = title;
        this.body = body;
        this.missionId = missionId;
    }
    public Register toEntity(User user, Mission mission) {
        return Register.builder()
                .user(user)
                .title(title)
                .body(body)
                .mission(mission)
                .status(RegisterStatus.PROGRESS)
                .build();
    }
}
