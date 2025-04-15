package com.liu.edvinasCinemaEvents.controller;


import com.liu.edvinasCinemaEvents.model.Screening;
import com.liu.edvinasCinemaEvents.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;
    private final CacheManager cacheManager;

    @PostMapping("/{cinemaId}")
    public ResponseEntity<Screening> addScreening(
            @RequestBody Screening screening,
            @PathVariable Long cinemaId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningService.addScreening(screening, cinemaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable Long id) {

        return ResponseEntity.ok(screeningService.getScreeningById(id));
    }


}
