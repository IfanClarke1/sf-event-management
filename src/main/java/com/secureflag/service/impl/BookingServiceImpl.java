package com.secureflag.service.impl;

import com.secureflag.application_events.BookingCancelledEvent;
import com.secureflag.application_events.BookingCreatedEvent;
import com.secureflag.dao.BookingsRepository;
import com.secureflag.dto.BookEventDto;
import com.secureflag.dto.BookingDto;
import com.secureflag.entity.BookingWaitlist;
import com.secureflag.entity.Bookings;
import com.secureflag.entity.Events;
import com.secureflag.enums.BookingStatus;
import com.secureflag.exceptions.EventFullyBookedException;
import com.secureflag.exceptions.NotFoundException;
import com.secureflag.service.BookingService;
import com.secureflag.service.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final EventService eventService;

    private final BookingsRepository bookingsRepository;

    private final ApplicationEventPublisher publisher;


    @Override
    @Transactional
    public BookingDto bookEvent(Long userId, BookEventDto bookEventDto) throws EventFullyBookedException, NotFoundException {

        Events event = eventService.findByReferenceWithLock(bookEventDto.getEventReference())
                .orElseThrow(() -> new NotFoundException("Event not found"));

        if (event.getAvailableCapacity() <= 0) {
            if (bookEventDto.isShouldWaitList()) {
                bookEventDto.setStatus(BookingStatus.WAIT_LISTED);
            } else {
                throw new EventFullyBookedException("No seats available for this event");
            }
        } else {
            bookEventDto.setStatus(BookingStatus.SUCCESSFUL);
        }

        Bookings newBooking = createBooking(userId, event.getId(), event.getAmount(), bookEventDto);

        updateEventAvailableCapacity(event);

        publish(new BookingCreatedEvent(this, newBooking));

        return BookingDto.fromModel(newBooking, event);
    }

    @Override
    @Transactional
    public void CancelBooking(Long userId, String reference) throws NotFoundException {
        Bookings booking = bookingsRepository.findByUserIdAndReference(userId, reference)
                .orElseThrow(() -> new NotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingsRepository.save(booking);

        publish(new BookingCancelledEvent(this, booking));
    }

    @Transactional
    public Bookings updateBookingStatus(Long bookingId, BookingStatus status) throws NotFoundException {
        Bookings booking = bookingsRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));

        booking.setStatus(status);

        return bookingsRepository.save(booking);
    }

    @Override
    @Transactional
    public Bookings processWaitList(Bookings cancelledBooking) throws NotFoundException {
       BookingWaitlist waitlistBooking = bookingsRepository.findFirstWaitListedBooking(cancelledBooking.getReference())
               .orElseThrow(() -> new NotFoundException("Booking not found"));

        cancelledBooking.setStatus(BookingStatus.SUCCESSFUL);
        cancelledBooking.setUserId(waitlistBooking.getId());

        Bookings successfulBooking = bookingsRepository.save(cancelledBooking);

        publish(new BookingCreatedEvent(this, cancelledBooking));

       return successfulBooking;
    }

    private Bookings createBooking(Long userId, Long eventId, BigDecimal eventAmount, BookEventDto bookEventDto) {
        Bookings booking = new Bookings();
        booking.setUserId(userId);
        booking.setType(bookEventDto.getBookingType());
        booking.setStatus(bookEventDto.getStatus());
        booking.setEventId(eventId);
        booking.setFee(eventAmount);
        return bookingsRepository.save(booking);
    }

    private void updateEventAvailableCapacity(Events event) {
        event.setAvailableCapacity(event.getAvailableCapacity() - 1);
        eventService.upsert(event);
    }

    private void publish(ApplicationEvent event) {
        CompletableFuture.runAsync(() -> publisher.publishEvent(event));
    }
}
