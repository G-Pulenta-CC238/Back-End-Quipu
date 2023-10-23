package gpulenta.quipu.offer.service;

import gpulenta.quipu.offer.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);
    Message save(Message message);
    Message update(Message message);
    void delete(Long id);
    List<Message> findByOffer_Id(Long id);
    List<Message> findByUserId(Long userId);
}
