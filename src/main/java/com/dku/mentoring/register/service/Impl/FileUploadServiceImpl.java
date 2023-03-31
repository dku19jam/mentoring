package com.dku.mentoring.register.service.Impl;

import com.dku.mentoring.register.model.entity.RegisterFile;
import com.dku.mentoring.register.service.FileUploadService;
import com.dku.mentoring.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private static final String DOWNLOAD_ROOT = System.getProperty("user.home") + File.separator + "mentoring" + File.separator + "images";
    private final RegisterFileRepository registerFileRepository;



    @Override
    public List<RegisterFile> uploadFiles(List<MultipartFile> files, User user) {
        ArrayList<RegisterFile> fileList = new ArrayList<>();

        String dir = DOWNLOAD_ROOT + File.separator + user.getStudentId();
        boolean makeDir = makeDir(dir);
        List<RegisterFile> images = new ArrayList<>();

        files.forEach(multipartFile->{
            String originName = multipartFile.getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf(".") + 1);
            String fileName = "matching" + "-" + UUID.randomUUID() + "." + ext;
            File file = new File(dir, fileName);
            try {
                multipartFile.transferTo(file);

                RegisterFile registerFile = RegisterFile.builder()
                        .fileUrl(dir)
                        .fileName(fileName)
                        .fileName(fileName)
                        .build();
                registerFileRepository.save(registerFile);
                images.add(registerFile);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        return images;
    }

    private boolean makeDir(String dir) {
        return false;
    }

    @Override
    public String uploadFile(MultipartFile file, User user) {
        return null;
    }

    @Override
    public void deleteFiles(List<RegisterFile> files) {
    }

    @Override
    public void deleteFile(String fileId) {
    }

    @Override
    public String getBaseURL() {
        return null;
    }

}
