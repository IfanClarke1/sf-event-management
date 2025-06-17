package com.secureflag.entity;

import com.secureflag.enums.BookingStatus;
import com.secureflag.enums.BookingType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Table(name = "bookings")
public class Bookings extends BaseEntity {

    private BigDecimal fee;

    private BookingStatus status;

    @Column( nullable = true)
    private Integer admissionNumber;// alias for sit number if available

    private Long userId;

    private Long eventId;

    private BookingType type;
}