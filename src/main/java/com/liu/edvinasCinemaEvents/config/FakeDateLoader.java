package com.liu.edvinasCinemaEvents.config;

import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import com.liu.edvinasCinemaEvents.repository.ScreeningRepository;
import com.liu.edvinasCinemaEvents.repository.TicketBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FakeDateLoader implements CommandLineRunner {

    private final ScreeningRepository screeningRepository;
    private final CacheManager cacheManager;


    @Override
    public void run(String... args) throws Exception {

        Cache screeningCache = cacheManager.getCache("screening");

        Cinema cinema = Cinema.builder()
                .name("CINEMA")
                .location("Vilnius")
                .build();

        Screening screening = Screening.builder()
                .availableSeats(300)
                .movieTitle("Jack ass")
                .starTime(Timestamp.valueOf(LocalDateTime.of(2025, 5, 1, 22, 0, 0)))
                .build();


        cinema.setScreenings(List.of(screening));
        screening.setCinema(cinema);

        screeningRepository.save(screening);
        screeningCache.put(screening.getId(),screening);

    }
}
