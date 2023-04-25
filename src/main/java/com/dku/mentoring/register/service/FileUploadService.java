package com.dku.mentoring.register.service;

import com.dku.mentoring.register.exception.RegisterNotfoundException;
import com.dku.mentoring.register.model.entity.Register;
import com.dku.mentoring.register.model.entity.RegisterFile;
import com.dku.mentoring.register.repository.RegisterFileRepository;
import com.dku.mentoring.register.repository.RegisterRepository;
import com.dku.mentoring.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private static final String DOWNLOAD_ROOT =
            System.getProperty("user.home") + File.separator + "dku" + File.separator + "mentoring" + File.separator + "images";

    private final RegisterRepository registerRepository;
    private final RegisterFileRepository registerFileRepository;

    public List<RegisterFile> uploadImage(List<MultipartFile> files, User user, Long registerId) {
        ArrayList<RegisterFile> registerFiles = new ArrayList<>();
        String dir = DOWNLOAD_ROOT + File.separator + user.getStudentId();
        boolean makeDir = makeDir(dir);

        files.forEach(multipartFile -> {
            String originName = multipartFile.getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf(".") + 1);
            String fileName = user.getName() + "-" + UUID.randomUUID() + "." + ext;
            File file = new File(dir, fileName);

            Register register = registerRepository.findById(registerId).orElseThrow(RegisterNotfoundException::new);
            RegisterFile registerFile = RegisterFile.builder()
                    .fileName(fileName)
                    .fileUrl(dir)
                    .register(register)
                    .build();

            registerFileRepository.save(registerFile);
            registerFiles.add(registerFile);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return registerFiles;
    }

    public boolean makeDir(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists()) {
            return dir.mkdirs();
        } else if (dir.exists()) {
            return true;
        }

        return false;
    }

    public byte[] loadImage(String dir, String fileName) {
        InputStream fileInputStream;
        byte[] image;
        try {
            fileInputStream = new FileInputStream(dir + File.separator + fileName);
            image = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
