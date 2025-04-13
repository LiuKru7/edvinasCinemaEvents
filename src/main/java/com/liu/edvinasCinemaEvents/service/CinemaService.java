package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public Cinema addCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

}
