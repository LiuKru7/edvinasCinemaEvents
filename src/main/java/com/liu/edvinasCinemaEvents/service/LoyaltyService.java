package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoyaltyService implements ApplicationListener<TicketBookingEvent> {

    private final Map<String, Integer>  loyaltyPoints = new HashMap<>();

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(TicketBookingEvent event) {
        var ticketBooking = event.getTicketBooking();
        String name = ticketBooking.getViewerName();
        int currentPoints = loyaltyPoints.getOrDefault(name, 0);
        int updatedPoints = currentPoints + ticketBooking.getSeatCount();
        loyaltyPoints.put(name, updatedPoints);
        log.info("Viewer: {} have {} points. ", name, updatedPoints);
    }
}
