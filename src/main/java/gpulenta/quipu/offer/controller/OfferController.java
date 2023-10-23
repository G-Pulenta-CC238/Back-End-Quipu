package gpulenta.quipu.offer.controller;

import gpulenta.quipu.offer.model.Offer;
import gpulenta.quipu.offer.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/offer")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Create offer
    @Operation(summary = "Create offer", description = "Create a new offer")
    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        return new ResponseEntity<>(offerService.save(offer), HttpStatus.CREATED);
    }
    // Find offer by user ID
    @Operation(summary = "Get offer by user ID", description = "Get a offer details by user ID")
    @GetMapping("/find-by-userid/{id}")
    public ResponseEntity<Offer> getOfferByUserId(@PathVariable Long id) {
        Offer offer = offerService.findByUserId(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    // Find offer by ID
    @Operation(summary = "Get offer by ID", description = "Get a offer details by ID")
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    // Update offer
    @Operation(summary = "Update offer by ID", description = "Update an existing offer's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody Offer offer) {
        return new ResponseEntity<>(offerService.update(offer), HttpStatus.OK);
    }
    // Delete offer
    @Operation(summary = "Delete offer", description = "Delete an existing offer")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        if (offerService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        offerService.delete(id);
        return new ResponseEntity<>("Offer deleted successfully", HttpStatus.OK);
    }
    // Find all
    @Operation(summary = "Get all offers", description = "Get all offers details")
    @GetMapping
    public ResponseEntity<Iterable<Offer>> getAllOffers() {
        if (offerService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(offerService.findAll(), HttpStatus.OK);
    }

}
