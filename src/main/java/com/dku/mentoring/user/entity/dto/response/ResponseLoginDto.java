package com.dku.mentoring.user.entity.dto.response;

import com.dku.mentoring.user.entity.User;
import com.dku.mentoring.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseLoginDto {
    private  String accessToken;
    private  String refreshToken;
    private List<UserRole> roles = new ArrayList<>();
    private final String studentId;
    private final String name;
    private final String teamName;

    public ResponseLoginDto(User user) {
        this.studentId = user.getStudentId();
        this.name = user.getName();
        this.teamName = user.getTeam().getTeamName();
        this.roles = user.getRoles();
    }
}
