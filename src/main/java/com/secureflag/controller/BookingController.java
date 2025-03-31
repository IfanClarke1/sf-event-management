package com.secureflag.controller;

import com.secureflag.dto.BookEventDto;
import com.secureflag.exceptions.EventFullyBookedException;
import com.secureflag.exceptions.NotFoundException;
import com.secureflag.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> handleEventBooking(@RequestHeader(name = "userId") Long userId,
                                                @RequestBody BookEventDto bookEventDto) throws EventFullyBookedException, NotFoundException {
        return ResponseEntity.ok(bookingService.bookEvent(userId, bookEventDto));
    }

    @PutMapping("/{bookingReference}/cancel")
    public ResponseEntity<?> handleEventBooking(@RequestHeader(name = "userId") Long userId,
                                                @PathVariable("bookingReference") String reference) throws NotFoundException {
        bookingService.CancelBooking(userId, reference);
        return ResponseEntity.ok().build();
    }
}
