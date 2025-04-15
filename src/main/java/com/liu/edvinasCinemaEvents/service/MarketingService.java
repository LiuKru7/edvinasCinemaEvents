package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketingService implements ApplicationListener<TicketBookingEvent> {

    private final CacheService cacheService;

    @Override
    public void onApplicationEvent(TicketBookingEvent event) {
        var screening = cacheService.getScreeningById(event.getTicketBooking().getScreeningId());
        log.info("Viewer {}: Booking {} seats for movie: {}",
                event.getTicketBooking().getViewerName(),
                event.getTicketBooking().getSeatCount(),
                screening.getMovieTitle());
    }
}
