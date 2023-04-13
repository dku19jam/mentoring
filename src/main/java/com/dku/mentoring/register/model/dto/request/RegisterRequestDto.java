package com.dku.mentoring.register.model.dto.request;

import com.dku.mentoring.mission.model.dto.request.MissionBonusRequestDto;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    @Schema(description = "제목", example = "제목")
    private String title;

    @NotBlank
    @Schema(description = "내용")
    private String body;

    @Schema(description = "추가 미션 리스트")
    private List<MissionBonusRequestDto> missionList = new ArrayList<>();


    @Builder
    public RegisterRequestDto(String title, String body, List<MissionBonusRequestDto> missionList) {
        this.title = title;
        this.body = body;
        this.missionList = missionList;
    }
    public Register toEntity(User user, Mission mission) {
        return Register.builder()
                .user(user)
                .title(title)
                .body(body)
                .mission(mission)
                .build();
    }
}
