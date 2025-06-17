package com.secureflag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(length = 64, nullable = false, updatable = false, unique = true)
    private String reference;

    @PrePersist
    public void appendReference(){
        if (!StringUtils.hasText(this.reference)) {
            this.reference = UUID.randomUUID().toString().replaceAll("-", "");
        }
    }
}
