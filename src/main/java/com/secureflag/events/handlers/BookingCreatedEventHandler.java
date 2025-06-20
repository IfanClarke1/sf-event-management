package com.secureflag.events.handlers;

import com.secureflag.events.BookingCreatedEvent;
import com.secureflag.entity.Bookings;
import com.secureflag.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingCreatedEventHandler {

    private final NotificationService notificationService;

    @Async
    @EventListener
    public void handleBookingCreatedEvent(BookingCreatedEvent bookingCreatedEvent){
        Bookings booking = bookingCreatedEvent.getBookings();
        log.info("New booking created {}", booking);
        notificationService.notify(booking.getUserId(), booking.getEventId());
    }
}
