package com.secureflag.dto;

import com.secureflag.entity.Events;
import com.secureflag.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String theme;

    private String reference;

    private Date createdAt;

    private BigDecimal amount;

    private EventStatus status;

    private String venue;

    private int totalCapacity;

    private int availableCapacity;

    private Date startTime;

    private Date endTime;

    public static EventDto fromModel(Events events){
        EventDto eventDto = new EventDto();
        BeanUtils.copyProperties(events, eventDto);
        return eventDto;
    }

    public static Object fromModel(Object o) {
        return fromModel((Events) o);
    }
}
