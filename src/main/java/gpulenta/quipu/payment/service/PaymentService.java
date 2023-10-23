package gpulenta.quipu.payment.service;

import gpulenta.quipu.payment.model.Payment;

public interface PaymentService {
    Payment findByUserId(Long userId);

    Payment findById(Long id);

    Payment save(Payment payment);

    Payment update(Payment payment);

    void delete(Long id);
}
