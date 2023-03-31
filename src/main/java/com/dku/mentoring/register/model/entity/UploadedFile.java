package com.dku.mentoring.register.model.entity;

import lombok.Getter;

@Getter
public class UploadedFile {
    private final String fileId;

    private final String originalName;

    public UploadedFile(String fileId, String originalName) {
        this.fileId = fileId;
        this.originalName = originalName;
    }

    public static UploadedFile of(RegisterFile entity) {
        return new UploadedFile(entity.getFileId(), entity.getFileName());
    }
}
