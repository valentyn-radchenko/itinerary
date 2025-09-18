package org.mohyla.itinerary.payments.domain.events;

public record PaymentCreatedEvent(Long paymentId, Long ticketId, Long userId, double amount) {}
