package com.secureflag.application_events.handlers;

import com.secureflag.application_events.BookingCancelledEvent;
import com.secureflag.entity.Bookings;
import com.secureflag.exceptions.NotFoundException;
import com.secureflag.service.BookingService;
import com.secureflag.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingCancelledEventHandler {

    private final NotificationService notificationService;

    private final BookingService bookingService;

    @Async
    @EventListener
    public void handleBookingCancelledEvent(BookingCancelledEvent bookingCancelledEvent) throws NotFoundException {
        Bookings cancelledEventBooking = bookingCancelledEvent.getBookings();

        log.info("booking cancelled {}", cancelledEventBooking);

        bookingService.processWaitList(cancelledEventBooking);

        //sent booking cancelled notification
        notificationService.notify(cancelledEventBooking.getUserId(), cancelledEventBooking.getEventId());
    }
}
