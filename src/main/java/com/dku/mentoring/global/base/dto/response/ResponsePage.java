package com.dku.mentoring.global.base.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
public class ResponsePage<T> implements Serializable {

    @Schema(description = "페이지 내용")
    private final List<T> content;

    @Schema(description = "총 페이지 수")
    private final int totalPages;

    @Schema(description = "총 요소 수")
    private final long totalElements;

    @Schema(description = "페이지 번호")
    private final int pageNumber;

    @Schema(description = "페이지 크기")
    private final int pageSize;

    public ResponsePage(Page<T> page) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
