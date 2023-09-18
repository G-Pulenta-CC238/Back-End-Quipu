package com.gpulenta.quipu.trips.controller;

import com.gpulenta.quipu.trips.model.Trip;
import com.gpulenta.quipu.trips.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @Operation(summary = "Get a trip by id", description = "Get a trip's details by their id")
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable int id) {
        Trip trip = tripService.getTripById(id);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Get all trips", description = "Get all trip's details")
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
    @Operation(summary = "Create a new trip", description = "Create a new trip with the provided information")
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip newTrip = tripService.saveTrip(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }
    @Operation(summary = "Delete trip by ID", description = "Delete a trip by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
