package com.gpulenta.quipu.trips.service;

import com.gpulenta.quipu.trips.model.Trip;
import com.gpulenta.quipu.trips.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(int id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(int id) {
        tripRepository.deleteById(id);
    }
}
