package com.dku.mentoring.register.model.dto.request;

import com.dku.mentoring.mission.model.dto.request.MissionBonusRequestDto;
import com.dku.mentoring.mission.model.entity.Mission;
import com.dku.mentoring.mission.model.entity.MissionBonus;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    @Schema(description = "제목", example = "제목")
    private String title;

    @NotBlank
    @Schema(description = "내용")
    private String body;

    private List<MultipartFile> files = new ArrayList<>();


    public Register toEntity(User user, Mission mission) {
        return Register.builder()
                .user(user)
                .title(title)
                .body(body)
                .totalScore(mission.getPoint())
                .mission(mission)
                .build();
    }
}
