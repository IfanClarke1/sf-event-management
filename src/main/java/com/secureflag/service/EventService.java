package com.secureflag.service;

import com.secureflag.dto.EventDto;
import com.secureflag.dto.PageDto;
import com.secureflag.entity.Events;

import java.util.Optional;


public interface EventService {

    EventDto create(EventDto eventDto);

    PageDto listEvents(int page, int size);

    Optional<Events> findByReferenceWithLock(String eventReference);

    void upsert(Events events);
}
