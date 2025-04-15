package com.liu.edvinasCinemaEvents.repository;

import com.liu.edvinasCinemaEvents.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
