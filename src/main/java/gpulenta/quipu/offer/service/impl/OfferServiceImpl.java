package gpulenta.quipu.offer.service.impl;

import gpulenta.quipu.offer.model.Offer;
import gpulenta.quipu.offer.repository.OfferRepository;
import gpulenta.quipu.offer.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;


    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    @Override
    public Offer findByUserId(Long userId) {
        return offerRepository.findByUserId(userId);
    }

    @Override
    public Offer findById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer update(Offer offer) {
        Offer offerToUpdate = offerRepository.findById(offer.getId()).orElse(null);
        if (offerToUpdate != null) {
            offerToUpdate.setUserId(offer.getUserId());
            offerToUpdate.setOfferPrice(offer.getOfferPrice());
            offerToUpdate.setOfferStatus(offer.getOfferStatus());
            return offerRepository.save(offerToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }
}
