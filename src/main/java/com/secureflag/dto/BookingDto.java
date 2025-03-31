package com.secureflag.dto;

import com.secureflag.entity.Bookings;
import com.secureflag.entity.Events;
import com.secureflag.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private String reference;

    private Date createdAt;

    private BigDecimal fee;

    private BookingStatus status;

    private Integer admissionNumber;

    public static BookingDto fromModel(Bookings booking){
        BookingDto bookingDto = new BookingDto();
        BeanUtils.copyProperties(booking, bookingDto);
        return bookingDto;
    }

    public static Object fromModel(Object o) {
        return fromModel((BookingDto) o);
    }
}
