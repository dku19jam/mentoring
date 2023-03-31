package com.dku.mentoring.base;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;

@Getter
public class ResponsePage<T> implements Serializable {

    private final List<T> context;

    private final boolean hasNext;

    private final int totalPages;

    private final long totalElements;

    private final int page;

    private final int size;

    private final boolean first;

    private final boolean last;

    public ResponsePage(Page<T> page) {
        final PageImpl<T> pageInfo = new PageImpl<>(page.getContent(), page.getPageable(), page.getTotalElements());
        this.context = pageInfo.getContent();
        this.hasNext = pageInfo.hasNext();
        this.totalPages = pageInfo.getTotalPages();
        this.totalElements = pageInfo.getTotalElements();
        this.page = pageInfo.getNumber() + 1;
        this.size = pageInfo.getSize();
        this.first = pageInfo.isFirst();
        this.last = pageInfo.isLast();
    }
}
