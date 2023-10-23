package gpulenta.quipu.offer.service.impl;

import gpulenta.quipu.offer.model.Message;
import gpulenta.quipu.offer.repository.MessageRepository;
import gpulenta.quipu.offer.service.MessageService;
import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
    }
    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        Message messageToUpdate = messageRepository.findById(message.getId()).orElse(null);
        if (messageToUpdate != null) {
            messageToUpdate.setUserId(message.getUserId());
            messageToUpdate.setMessage(message.getMessage());
            return messageRepository.save(messageToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);

    }

    @Override
    public List<Message> findByOffer_Id(Long id) {
        return messageRepository.findByOffer_Id(id);
    }

    @Override
    public List<Message> findByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }
}
