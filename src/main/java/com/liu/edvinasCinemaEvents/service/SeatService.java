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
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatService implements ApplicationListener<TicketBookingEvent> {

    private final CacheManager cacheManager;
    private final ScreeningRepository screeningRepository;

    //todo change exception
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(TicketBookingEvent event) {
        Cache cache = cacheManager.getCache("screening");
        Long screeningId = event.getTicketBooking().getScreeningId();
        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(()-> new RuntimeException("Screening was not found by id : " + screeningId));
        if (screening.getAvailableSeats() < event.getTicketBooking().getSeatCount()) {
            throw new RuntimeException("Screening have not enought seats. Seats left only: " + event.getTicketBooking().getSeatCount());
        }
        screening.setAvailableSeats(screening.getAvailableSeats() - event.getTicketBooking().getSeatCount());
        screeningRepository.save(screening);

        cache.evict(screening.getId());
        cache.put(screening.getId(), screening);
    }
}
