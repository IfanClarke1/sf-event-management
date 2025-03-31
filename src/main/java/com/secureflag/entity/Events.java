package com.secureflag.entity;

import com.secureflag.enums.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity(name = "events")
public class Events extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private String venue;

    private String theme;

    private int totalCapacity;

    private int availableCapacity;

    private Date startTime;

    private Date endTime;
}
