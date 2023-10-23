package gpulenta.quipu.payment.controller;


import gpulenta.quipu.payment.model.Payment;
import gpulenta.quipu.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create payment
    @Operation(summary = "Create payment", description = "Create a new payment")
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        if (paymentService.save(payment) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(paymentService.save(payment), HttpStatus.CREATED);
    }

    // Update payment
    @Operation(summary = "Update payment by ID", description = "Update an existing payment's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        if (paymentService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        payment.setId(id);
        return new ResponseEntity<>(paymentService.update(payment), HttpStatus.OK);
    }

    // Delete payment
    @Operation(summary = "Delete payment", description = "Delete an existing payment")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        if (paymentService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        paymentService.delete(id);
        return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
    }

    // Find payment by user ID
    @Operation(summary = "Get payment by user ID", description = "Get a payment details by user ID")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Payment> getPaymentByUserId(@PathVariable Long userId) {
        Payment payments = paymentService.findByUserId(userId);
        if (payments == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // Find payment by ID
    @Operation(summary = "Get payment by ID", description = "Get a payment details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        if (paymentService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);
    }
}
