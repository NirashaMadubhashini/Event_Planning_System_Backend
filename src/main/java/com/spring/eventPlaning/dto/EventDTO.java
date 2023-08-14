package com.spring.eventPlaning.dto;

import lombok.Data;

@Data
public class EventDTO {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private Long eventPrice;
}
