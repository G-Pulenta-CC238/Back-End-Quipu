package gpulenta.quipu.offer.repository;

import gpulenta.quipu.offer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByOffer_Id(Long id);

    List<Message> findByUserId(Long userId);
}
