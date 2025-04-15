package com.liu.edvinasCinemaEvents.repository;

import com.liu.edvinasCinemaEvents.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
