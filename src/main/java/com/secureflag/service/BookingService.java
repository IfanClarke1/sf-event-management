package com.secureflag.service;

import com.secureflag.dto.BookEventDto;
import com.secureflag.dto.BookingDto;
import com.secureflag.entity.Bookings;
import com.secureflag.enums.BookingStatus;
import com.secureflag.exceptions.EventFullyBookedException;
import com.secureflag.exceptions.NotFoundException;

public interface BookingService {
    BookingDto bookEvent(Long userId, BookEventDto bookEventDto) throws EventFullyBookedException, NotFoundException;
    void CancelBooking(Long userId, String reference)throws NotFoundException;

    Bookings processWaitList(Bookings bookings) throws NotFoundException;
}
