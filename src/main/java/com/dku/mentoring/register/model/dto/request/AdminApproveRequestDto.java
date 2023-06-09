package com.dku.mentoring.register.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminApproveRequestDto {

    @Schema(name = "관리자 추가 점수" , example = "0")
    private int adminBonusPoint;

    public AdminApproveRequestDto(int adminBonusPoint) {
        this.adminBonusPoint = adminBonusPoint;
    }
}
