package com.liu.edvinasCinemaEvents.service;


import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import com.liu.edvinasCinemaEvents.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final CinemaRepository cinemaRepository;
    private final CacheManager cacheManager;

    //todo --- need change exception
    public Screening addScreening(Screening screening, Long cinemaId) {
        Cache screeningCache = cacheManager.getCache("screening");
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
        screening.setCinema(cinema);
        Screening savedScreening = screeningRepository.save(screening);
        screeningCache.put(savedScreening.getId(), savedScreening);
        return savedScreening;
    }
    //todo --- need change exception
    public Screening getScreeningById(Long id) {
        Cache screeningCache = cacheManager.getCache("screening");
        if (screeningCache != null) {
            Screening screeningFromCache = screeningCache.get(id,Screening.class);
            if (screeningFromCache != null) {
                return screeningFromCache;
            }
        }
        Screening screening = screeningRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Screening not found by id"));

        if (screeningCache != null) {
            screeningCache.put(screening.getId(), screening);
        }

        return screening;

    }
}
