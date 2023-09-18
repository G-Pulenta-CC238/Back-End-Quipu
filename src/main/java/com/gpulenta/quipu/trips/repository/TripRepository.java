package com.gpulenta.quipu.trips.repository;

import com.gpulenta.quipu.trips.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
