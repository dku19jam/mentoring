package com.dku.mentoring.register.model.entity;

import com.dku.mentoring.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RegisterImage extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "register_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", nullable = false)
    private Register register;

    private String fileName;

    public RegisterImage(Register register, String fileName) {
        this.register = register;
        this.fileName = fileName;
    }
}
