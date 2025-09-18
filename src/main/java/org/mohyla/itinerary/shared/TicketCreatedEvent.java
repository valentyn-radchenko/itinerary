package org.mohyla.itinerary.shared;

public record TicketCreatedEvent(Long ticketId, Long userId, Long routeId) { }
