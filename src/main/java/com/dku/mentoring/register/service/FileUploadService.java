package com.dku.mentoring.register.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile file, String prefix);

    void deleteFile(String fileId);

    String getBaseURL();
}
