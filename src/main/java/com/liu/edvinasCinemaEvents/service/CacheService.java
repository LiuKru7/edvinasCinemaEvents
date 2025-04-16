package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.events.TicketBookingEvent;
import com.liu.edvinasCinemaEvents.model.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheManager cacheManager;

    public Screening getScreeningById(Long id) {
        Cache cache = cacheManager.getCache("screening");
        if (cache != null) {
            Screening screening = cache.get(id, Screening.class);
            if (screening != null) {
                return screening;
            }
        }
        throw new RuntimeException("Screening not found in cache for id " + id);
    }
}
