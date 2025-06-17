package com.secureflag.dto;

import com.secureflag.enums.EventStatus;

import java.math.BigDecimal;
import java.util.Date;

public interface EventProjection {
    Long getId();
    String getReference();
    BigDecimal getAmount();
    EventStatus getStatus();
    String getVenue();
    String getTheme();
    Integer getTotalCapacity();
    Integer getAvailableCapacity();
    Date getStartTime();
    Date getEndTime();
}
