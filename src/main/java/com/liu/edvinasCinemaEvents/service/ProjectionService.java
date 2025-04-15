package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.EventListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectionService implements ApplicationListener<TicketBookingEvent> {


    private final CacheService cacheService;

    @Override
    public void onApplicationEvent(TicketBookingEvent event) {
        Screening screening = cacheService.getScreeningById(event.getTicketBooking().getScreeningId());
        log.info("Projection notified for : \"{}\" at TIME: {}", screening.getMovieTitle(), screening.getStarTime());
    }
}
