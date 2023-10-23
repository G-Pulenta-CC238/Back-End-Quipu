package gpulenta.quipu.offer.controller;

import gpulenta.quipu.offer.model.Message;
import gpulenta.quipu.offer.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Create message
    @Operation(summary = "Create message", description = "Create a new message")
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.save(message), HttpStatus.CREATED);
    }
    // Find message by user ID
    @Operation(summary = "Get message by user ID", description = "Get a message details by user ID")
    @GetMapping("/find-by-userid/{id}")
    public ResponseEntity<Iterable<Message>> getMessageByUserId(@PathVariable Long id) {
        if (messageService.findByUserId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(messageService.findByUserId(id), HttpStatus.OK);
    }
    // Find message by ID
    @Operation(summary = "Get message by ID", description = "Get a message details by ID")
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageService.findById(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    // Update message
    @Operation(summary = "Update message by ID", description = "Update an existing message's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        return new ResponseEntity<>(messageService.update(message), HttpStatus.OK);
    }
    // Delete message
    @Operation(summary = "Delete message", description = "Delete an existing message")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        if (messageService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        messageService.delete(id);
        return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
    }
    // list by offer id
    @Operation(summary = "Get message by offer ID", description = "Get a message details by offer ID")
    @GetMapping("/findbyofferid/{id}")
    public ResponseEntity<Iterable<Message>> getMessageByOfferId(@PathVariable Long id) {
        if (messageService.findByOffer_Id(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(messageService.findByOffer_Id(id), HttpStatus.OK);
    }
}
