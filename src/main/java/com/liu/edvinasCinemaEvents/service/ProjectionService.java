package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.EventListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectionService implements ApplicationListener<TicketBookingEvent> {

    private final CacheService cacheService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(TicketBookingEvent event) {
        Screening screening = cacheService.getScreeningById(event.getTicketBooking().getScreeningId());
        log.info("Projection notified for : \"{}\" at TIME: {}", screening.getMovieTitle(), screening.getStarTime());
    }
}
