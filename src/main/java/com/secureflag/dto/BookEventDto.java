package com.secureflag.dto;

import com.secureflag.enums.BookingStatus;
import com.secureflag.enums.BookingType;
import com.secureflag.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEventDto {

    private String eventReference;

    private BookingType bookingType;

    private boolean shouldWaitList;

    private BookingStatus status;
}
