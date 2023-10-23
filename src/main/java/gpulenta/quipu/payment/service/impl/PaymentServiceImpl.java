package gpulenta.quipu.payment.service.impl;

import gpulenta.quipu.payment.model.Payment;
import gpulenta.quipu.payment.repository.PaymentRepository;
import gpulenta.quipu.payment.service.PaymentService;
import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Payment findByUserId(Long userId) {
        Payment payment = paymentRepository.findByUserId(userId);
        if (payment != null) {
            payment.setUser(userRepository.findById(payment.getUserId()).orElse(null));
        }
        return payment;
    }

    @Override
    public Payment findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setUser(userRepository.findById(payment.getUserId()).orElse(null));
        }
        return payment;
    }

    @Override
    public Payment save(Payment payment) {
        User user = userRepository.findById(payment.getUserId()).orElse(null);
        if (user != null && paymentRepository.findByUserId(payment.getUserId()) == null) {
            payment.setUser(user);
            return paymentRepository.save(payment);
        } else {
            return null;
        }
    }

    @Override
    public Payment update(Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(payment.getId());
        if (existingPayment.isPresent()) {
            Payment existingPaymentValue = existingPayment.get();
            existingPaymentValue.setPaymentCvv(payment.getPaymentCvv());
            existingPaymentValue.setPaymentExpiration(payment.getPaymentExpiration());
            existingPaymentValue.setPaymentNumber(payment.getPaymentNumber());
            existingPaymentValue.setUserId(payment.getUserId());
            return paymentRepository.save(existingPaymentValue);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
