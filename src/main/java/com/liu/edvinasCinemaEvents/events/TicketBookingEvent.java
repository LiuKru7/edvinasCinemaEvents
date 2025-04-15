package com.liu.edvinasCinemaEvents.events;

import com.liu.edvinasCinemaEvents.model.TicketBooking;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
public class TicketBookingEvent extends ApplicationEvent {

    private final TicketBooking ticketBooking;

    public TicketBookingEvent(Object source, TicketBooking ticketBooking) {
        super(source);
        this.ticketBooking = ticketBooking;
    }

}