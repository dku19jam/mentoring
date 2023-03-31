package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RegisterFile extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "register_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", nullable = false)
    private Register register;

    private String fileId;

    private String fileName;

    public RegisterFile (String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    public RegisterFile(UploadedFile file) {
        this(file.getFileId(), file.getOriginalName());
    }

}
