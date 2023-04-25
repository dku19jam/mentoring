package com.dku.mentoring.user.entity.dto.response;

import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseLoginDto {

    @Schema(description = "access토큰")
    private  String accessToken;

    @Schema(description = "refresh토큰")
    private  String refreshToken;

    @Schema(description = "학번")
    private final String studentId;

    @Schema(description = "이름")
    private final String name;

    @Schema(description = "팀 이름")
    private final String teamName;

    public ResponseLoginDto(User user) {
        this.studentId = user.getStudentId();
        this.name = user.getName();
        this.teamName = user.getTeam().getTeamName();
    }
}
