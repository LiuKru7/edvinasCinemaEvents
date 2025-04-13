package com.liu.edvinasCinemaEvents.repository;

import com.liu.edvinasCinemaEvents.model.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
}
