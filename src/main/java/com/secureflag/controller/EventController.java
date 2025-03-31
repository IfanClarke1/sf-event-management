package com.secureflag.controller;

import com.secureflag.dto.EventDto;
import com.secureflag.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/events")
public class EventController {

    private final EventService eventService;


    @PostMapping
    public ResponseEntity<?> handleEventCreation(@RequestBody EventDto eventDto){
        return ResponseEntity.ok(eventService.create(eventDto));
    }

    @GetMapping
    public ResponseEntity<?> handleGettingEvents(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "20") int size){
        return ResponseEntity.ok(eventService.listEvents(page, size));
    }
}
