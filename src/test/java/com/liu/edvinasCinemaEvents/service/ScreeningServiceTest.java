package com.liu.edvinasCinemaEvents.service;

import com.liu.edvinasCinemaEvents.model.Cinema;
import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.repository.CinemaRepository;
import com.liu.edvinasCinemaEvents.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private ScreeningService screeningService;

    @Test
    void addScreening_shouldSaveScreening_whenCinemaExists() {
        // given
        Long cinemaId = 1L;
        Cinema cinema = new Cinema();
        cinema.setId(cinemaId);

        Screening screening = new Screening();
        screening.setId(1L);

        when(cinemaRepository.findById(cinemaId)).thenReturn(Optional.of(cinema));
        when(screeningRepository.save(screening)).thenReturn(screening);

        // when
        Screening result = screeningService.addScreening(screening, cinemaId);

        // then
        assertNotNull(result);
        verify(cinemaRepository).findById(cinemaId);
        verify(screeningRepository).save(screening);
        assertEquals(cinema, screening.getCinema());
    }

    @Test
    void addScreening_shouldThrowException_whenCinemaNotFound() {
        // given
        Long cinemaId = 99L;
        Screening screening = new Screening();

        when(cinemaRepository.findById(cinemaId)).thenReturn(Optional.empty());

        // when / then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> screeningService.addScreening(screening, cinemaId));

        assertEquals("Cinema not found", exception.getMessage());
        verify(cinemaRepository).findById(cinemaId);
        verify(screeningRepository, never()).save(any());
    }

    @Test
    void getScreeningById_shouldReturnScreening_whenFound() {
        // given
        Long screeningId = 1L;
        Screening screening = new Screening();
        screening.setId(screeningId);

        when(screeningRepository.findById(screeningId)).thenReturn(Optional.of(screening));

        // when
        Screening result = screeningService.getScreeningById(screeningId);

        // then
        assertNotNull(result);
        assertEquals(screeningId, result.getId());
        verify(screeningRepository).findById(screeningId);
    }

    @Test
    void getScreeningById_shouldThrowException_whenNotFound() {
        // given
        Long screeningId = 100L;
        when(screeningRepository.findById(screeningId)).thenReturn(Optional.empty());

        // when / then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> screeningService.getScreeningById(screeningId));

        assertEquals("Screening not found by id", exception.getMessage());
        verify(screeningRepository).findById(screeningId);
    }
}
