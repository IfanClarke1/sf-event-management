package com.secureflag.dto;

import com.secureflag.entity.Bookings;
import com.secureflag.entity.Events;
import com.secureflag.enums.BookingStatus;
import com.secureflag.enums.BookingType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private String reference;

    private Date createdAt;

    private BigDecimal fee;

    private BookingStatus status;

    private Integer admissionNumber;

    private BookingType type;

    private String eventName;

    private String eventVenue;

    public static BookingDto fromModel(Bookings booking, Events event){
        BookingDto bookingDto = new BookingDto();
        BeanUtils.copyProperties(booking, bookingDto);
        bookingDto.setEventName(event.getTheme());
        bookingDto.setEventVenue(event.getVenue());
        return bookingDto;
    }

    public static Object fromModel(Object o) {
        return fromModel((BookingDto) o);
    }
}
