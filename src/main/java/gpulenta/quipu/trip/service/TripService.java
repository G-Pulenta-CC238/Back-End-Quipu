package gpulenta.quipu.trip.service;

import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.trip.model.TripDTO;

import java.util.List;

public interface TripService {
    List<TripDTO> findAll();

    TripDTO findById(Long id);


    Trip save(Trip trip);

    Trip update(Trip trip);

    void delete(Long id);

    List<TripDTO> findByUserId(Long userId);

}
