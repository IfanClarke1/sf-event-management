package com.secureflag.application_events;

import com.secureflag.entity.Bookings;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookingCreatedEvent extends ApplicationEvent {

    private Bookings bookings;

    public BookingCreatedEvent(Object source, Bookings bookings) {
        super(source);
        this.bookings = bookings;
    }
}
