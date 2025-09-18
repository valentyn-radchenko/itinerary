package org.mohyla.itinerary.shared;

public record PaymentCompletedEvent(Long ticketId, Long paymentId) {}

