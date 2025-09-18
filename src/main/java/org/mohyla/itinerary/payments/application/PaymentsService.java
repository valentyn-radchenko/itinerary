package org.mohyla.itinerary.payments.application;

import jakarta.transaction.Transactional;
import org.mohyla.itinerary.payments.domain.models.Payment;
import org.mohyla.itinerary.payments.domain.persistence.PaymentRepository;
import org.mohyla.itinerary.payments.domain.events.PaymentCreatedEvent;
import org.mohyla.itinerary.shared.PaymentCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsService {

    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher events;

    public PaymentsService(PaymentRepository paymentRepository, ApplicationEventPublisher events) {
        this.paymentRepository = paymentRepository;
        this.events = events;
    }

    @Transactional
    public void createPayment(Long ticketId, Long userId, double amount) {
        Payment payment = new Payment(ticketId, userId, amount);
        paymentRepository.save(payment);

        events.publishEvent(new PaymentCreatedEvent(payment.getId(), ticketId, userId, amount));


        System.out.println("Processing payment...");
        try {
            Thread.sleep(2000); // wait 5s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        payment.complete();
        paymentRepository.save(payment);

        events.publishEvent(new PaymentCompletedEvent(ticketId, payment.getId()));
        System.out.println("Payment Processed");
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
