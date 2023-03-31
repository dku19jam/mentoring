package com.dku.mentoring.register.service;

import com.dku.mentoring.register.model.entity.RegisterFile;
import com.dku.mentoring.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    List<RegisterFile> uploadFiles(List<MultipartFile> files, User user);
    String uploadFile(MultipartFile file, User user);
    void deleteFiles(List<RegisterFile> files);
    void deleteFile(String fileId);

    String getBaseURL();
}
