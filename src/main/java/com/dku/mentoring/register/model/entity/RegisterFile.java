package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.global.base.BaseEntity;
import lombok.Builder;
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

    private String fileUrl;

    @Builder

    public RegisterFile(Register register, String fileId, String fileName, String fileUrl) {
        this.register = register;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
