package com.secureflag.controller;

import com.secureflag.dto.BookEventDto;
import com.secureflag.exceptions.EventFullyBookedException;
import com.secureflag.exceptions.NotFoundException;
import com.secureflag.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
