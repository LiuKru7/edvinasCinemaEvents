package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CinemaServiceTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaService cinemaService;

    @Test
    void addCinemaTest() {
        Cinema cinema = Cinema.builder()
                .name("Name")
                .location("Location")
                .build();

        Cinema savedCinema = Cinema.builder()
                .id(1L)
                .name("Name")
                .location("Location")
                .build();

        when(cinemaRepository.save(cinema)).thenReturn(savedCinema);

        Cinema result = cinemaService.addCinema(cinema);

        verify(cinemaRepository).save(cinema);

        assertEquals("Name", result.getName());
        assertEquals("Location", result.getLocation());
    }
}