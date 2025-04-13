package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.model.TicketBooking;
import com.liu.edvinasCinemaEvents.repository.TicketBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketBookingService {

    private final TicketBookingRepository ticketBookingRepository;

    public TicketBooking bookTicket(TicketBooking ticketBooking) {

    }
}
