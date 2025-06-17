package com.secureflag.dao;

import com.secureflag.entity.BookingWaitlist;
import com.secureflag.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    @Query(value = """
            SELECT id, event_id, user_id, reference, fee, type, status, 
                   admission_number, created_at, updated_at
            FROM bookings
            WHERE user_id = :userId
              AND reference = :reference
            """, nativeQuery = true)
    Optional<Bookings> findByUserIdAndReference(@Param("userId") Long userId,
                                                @Param("reference") String reference);

    @Query(value = "SELECT id, status FROM bookings WHERE status = 'WAIT_LISTED' AND reference=:eventReference ORDER BY created_at ASC LIMIT 1 FOR UPDATE",
            nativeQuery = true)
    Optional<BookingWaitlist> findFirstWaitListedBooking(String eventReference);
}
