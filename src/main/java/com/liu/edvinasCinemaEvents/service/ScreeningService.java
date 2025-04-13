package com.liu.edvinasCinemaEvents.service;


import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import com.liu.edvinasCinemaEvents.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final CinemaRepository cinemaRepository;

    public Screening addScreening(Screening screening, Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(RuntimeException::new);
        cinema.getScreenings().add(screening);
        cinemaRepository.save(cinema);
        return screening;
    }
    //todo --- need change exception
    public Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElseThrow(()->new RuntimeException("Screening not found by id"));
    }
}
