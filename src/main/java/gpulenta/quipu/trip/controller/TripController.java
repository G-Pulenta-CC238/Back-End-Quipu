package gpulenta.quipu.trip.controller;

import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.trip.model.TripDTO;
import gpulenta.quipu.trip.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trip")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Find all trips
    @Operation(summary = "Get all trips", description = "Get all trips details")
    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        if (tripService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }

    // Find trip by id
    @Operation(summary = "Get trip by id", description = "Get a trip details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        TripDTO tripDTO = tripService.findById(id);
        if (tripDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tripDTO, HttpStatus.OK);
    }

    // Create trip
    @Operation(summary = "Create trip", description = "Create a new trip")
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        if (tripService.save(trip) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(tripService.save(trip), HttpStatus.CREATED);
    }

    // Update trip
    @Operation(summary = "Update trip by ID", description = "Update an existing trip's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        if (tripService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        trip.setId(id);
        return new ResponseEntity<>(tripService.save(trip), HttpStatus.OK);
    }

    // Delete trip
    @Operation(summary = "Delete trip", description = "Delete an existing trip")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {
        if (tripService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        tripService.delete(id);
        return new ResponseEntity<>("Trip deleted successfully", HttpStatus.OK);
    }

    // Find trips by user ID
    @Operation(summary = "Get trips by user ID", description = "Get trips associated with a user by their ID")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<TripDTO>> getTripsByUserId(@PathVariable Long userId) {
        if (tripService.findByUserId(userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tripService.findByUserId(userId), HttpStatus.OK);
    }
}
