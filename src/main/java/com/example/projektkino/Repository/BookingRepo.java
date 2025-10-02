package com.example.projektkino.Repository;


import com.example.projektkino.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo  extends JpaRepository<Booking,Integer> {
}


