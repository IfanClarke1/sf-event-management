package com.secureflag.dao;

import com.secureflag.dto.EventProjection;
import com.secureflag.entity.Events;
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
            ORDER BY e.created_at DESC
            """,
            countQuery = """
                    SELECT count(e.id)
                    FROM events e
                    WHERE e.status IN :statuses
                    """,
            nativeQuery = true)
    Page<EventProjection> findAllByStatusIn(
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM events e WHERE e.reference = :reference")
    Optional<Events> findByReferenceWithLock(@Param("reference") String reference);
}
