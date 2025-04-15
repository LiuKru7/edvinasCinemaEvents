package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatService implements ApplicationListener<TicketBookingEvent> {

    private final CacheManager cacheManager;

    @Override
    public void onApplicationEvent(TicketBookingEvent event) {
        Long screeningId = event.getTicketBooking().getScreeningId();
        Cache cache = cacheManager.getCache("screening");
        if (cache != null) {
            Screening screening = cache.get(screeningId, Screening.class);
            if (screening != null) {
                log.info("📢 [Marketing] Cache HIT for screening id {} in event listener", screeningId);
            }
        }
    }
}
