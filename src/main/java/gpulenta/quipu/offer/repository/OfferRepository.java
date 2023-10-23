package gpulenta.quipu.offer.repository;

import gpulenta.quipu.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findByUserId(Long id);
}
