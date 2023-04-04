package com.dku.mentoring.mission.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
public class MissionResponsePage<T> implements Serializable {
    @Schema(description = "페이지 내용")
    private final List<T> context;

    @Schema(description = "페이지 번호")
    private int pageNumber;

    @Schema(description = "페이지 크기")
    private int pageSize;

    @Schema(description = "총 페이지 수")
    private int totalPages;

    @Schema(description = "총 요소 수")
    private long totalElements;

    public MissionResponsePage(Page<T> page) {
        this.context = page.getContent();
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
