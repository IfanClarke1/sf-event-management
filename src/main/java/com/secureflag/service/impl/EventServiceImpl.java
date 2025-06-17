package com.secureflag.service.impl;

import com.secureflag.dao.EventsRepository;
import com.secureflag.dto.EventDto;
import com.secureflag.dto.EventProjection;
import com.secureflag.dto.PageDto;
import com.secureflag.entity.Events;
import com.secureflag.enums.EventStatus;
import com.secureflag.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventsRepository eventsRepository;

    @Override
    public EventDto create(EventDto eventDto) {

        Events newEvent = Events
                .builder()
                .venue(eventDto.getVenue())
                .amount(eventDto.getAmount())
                .endTime(eventDto.getEndTime())
                .theme(eventDto.getTheme())
                .startTime(eventDto.getStartTime())
                .status(EventStatus.OPEN)
                .totalCapacity(eventDto.getTotalCapacity())
                .availableCapacity(eventDto.getTotalCapacity())
                .build();

        return EventDto.fromModel(eventsRepository.save(newEvent));
    }

    @Override
    public PageDto listEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EventProjection> events = eventsRepository
                .findAllByStatusIn(Arrays.asList(EventStatus.OPEN.name(), EventStatus.FULLY_BOOKED.name()), pageable);
        return PageDto.build(events, EventDto::fromProjection);
    }

    @Override
    public Optional<Events> findByReferenceWithLock(String eventReference){
        return eventsRepository.findByReferenceWithLock(eventReference);
    }

    @Override
    public void upsert(Events events) {
        events.setUpdatedAt(new Date());
        eventsRepository.save(events);
    }
}
