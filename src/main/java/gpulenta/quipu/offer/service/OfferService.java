package gpulenta.quipu.offer.service;

import gpulenta.quipu.offer.model.Offer;

import java.util.List;

public interface OfferService {
    Offer findByUserId(Long userId);

    Offer findById(Long id);

    Offer save(Offer offer);

    Offer update(Offer offer);

    void delete(Long id);

    List<Offer> findAll();
}
