package com.secureflag.dao;

import com.secureflag.entity.Events;
import com.secureflag.enums.EventStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
    @Query(value = """
                   SELECT e.id, e.reference, e.amount, e.status, e.venue, e.theme,
                          e.total_capacity, e.available_capacity, e.start_time, e.end_time
                   FROM events e
                   WHERE e.status IN :statuses
                   ORDER BY e.start_time DESC
                   """, nativeQuery = true)
    Page<Events> findAllByStatusIn(List<EventStatus> statuses, Pageable pageable);

    @Query(value = """
                   SELECT e.id, e.reference, e.amount, e.status, e.venue, e.theme,
                          e.total_capacity, e.available_capacity, e.start_time, e.end_time
                   FROM events e
                   WHERE e.reference = :reference
                   """, nativeQuery = true)
    Optional<Events> findByReference(String reference);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM events e WHERE e.reference = :reference")
    Optional<Events> findByReferenceWithLock(@Param("reference") String reference);
}
