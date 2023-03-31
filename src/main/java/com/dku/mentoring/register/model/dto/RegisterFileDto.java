package com.dku.mentoring.register.model.dto;

import com.dku.mentoring.register.model.entity.RegisterFile;
import lombok.Getter;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RegisterFileDto {

    private final Long id;

    private final String imageUrl;

    private final String originalImageName;

    public RegisterFileDto(String baseUrl, RegisterFile file) {
        this.id = file.getId();
        this.imageUrl = URI.create(baseUrl + "/").resolve(file.getFileId()).toString();
        this.originalImageName = file.getFileName();
    }
    public static List<RegisterFileDto> listOf(String baseImageUrl, List<RegisterFile> entities) {
        return entities.stream()
                .map(file -> new RegisterFileDto(baseImageUrl, file))
                .collect(Collectors.toList());
    }
}
