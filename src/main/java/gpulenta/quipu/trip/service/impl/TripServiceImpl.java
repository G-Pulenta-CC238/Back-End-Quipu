package gpulenta.quipu.trip.service.impl;


import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.trip.model.TripDTO;
import gpulenta.quipu.trip.repository.TripRepository;
import gpulenta.quipu.trip.service.TripService;
import gpulenta.quipu.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    private final UserRepository userRepository;

    public TripServiceImpl(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TripDTO> findAll() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip trip : trips) {
            TripDTO tripDTO = new TripDTO();
            tripDTO.setId(trip.getId());
            tripDTO.setOrigin(trip.getOrigin());
            tripDTO.setDestination(trip.getDestination());
            tripDTO.setDate(trip.getDate());
            userRepository.findById(trip.getUserId()).ifPresent(user -> {
                tripDTO.setUsername(user.getUsername());
                tripDTO.setFirstName(user.getFirstName());
                tripDTO.setLastName(user.getLastName());
            });

            tripDTOs.add(tripDTO);
        }
        return tripDTOs;
    }


    @Override
    public TripDTO findById(Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null) {
            TripDTO tripDTO = new TripDTO();
            tripDTO.setId(trip.getId());
            tripDTO.setOrigin(trip.getOrigin());
            tripDTO.setDestination(trip.getDestination());
            tripDTO.setDate(trip.getDate());
            userRepository.findById(trip.getUserId()).ifPresent(user -> {
                tripDTO.setUsername(user.getUsername());
                tripDTO.setFirstName(user.getFirstName());
                tripDTO.setLastName(user.getLastName());
            });
            return tripDTO;
        } else {
            return null;
        }

    }



    @Override
    public Trip save(Trip trip) {
        if (userRepository.findById(trip.getUserId()).isPresent()) {
            return tripRepository.save(trip);
        } else {
            return null;
        }
    }

    @Override
    public Trip update(Trip trip) {
        Optional<Trip> existingTrip = tripRepository.findById(trip.getId());
        if (existingTrip.isPresent()) {
            Trip tripToUpdate = existingTrip.get();
            tripToUpdate.setOrigin(trip.getOrigin());
            tripToUpdate.setDestination(trip.getDestination());
            tripToUpdate.setDate(trip.getDate());
            return tripRepository.save(tripToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public List<TripDTO> findByUserId(Long userId) {
        List<Trip> trips = tripRepository.findByUserId(userId);
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip trip : trips) {
            TripDTO tripDTO = new TripDTO();
            tripDTO.setId(trip.getId());
            tripDTO.setOrigin(trip.getOrigin());
            tripDTO.setDestination(trip.getDestination());
            tripDTO.setDate(trip.getDate());
            userRepository.findById(trip.getUserId()).ifPresent(user -> {
                tripDTO.setUsername(user.getUsername());
                tripDTO.setFirstName(user.getFirstName());
                tripDTO.setLastName(user.getLastName());
            });

            tripDTOs.add(tripDTO);
        }
        return tripDTOs;
    }
}
