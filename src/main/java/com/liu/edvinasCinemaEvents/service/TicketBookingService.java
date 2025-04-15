package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.TicketBooking;
import com.liu.edvinasCinemaEvents.repository.TicketBookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketBookingService  {

    private final ApplicationEventPublisher eventPublisher;
    private final TicketBookingRepository ticketBookingRepository;

    public TicketBooking bookTicket(TicketBooking ticketBooking) {
        TicketBooking savedTicketBooking = ticketBookingRepository.save(ticketBooking);
        eventPublisher.publishEvent(new TicketBookingEvent(this,savedTicketBooking));
        return savedTicketBooking;
    }
}
