package org.mohyla.itinerary.payments.domain.persistence;

import org.mohyla.itinerary.payments.domain.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
