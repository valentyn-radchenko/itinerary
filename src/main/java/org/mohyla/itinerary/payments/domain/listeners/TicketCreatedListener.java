package org.mohyla.itinerary.payments.domain.listeners;

import org.mohyla.itinerary.shared.TicketCreatedEvent;
import org.mohyla.itinerary.payments.application.PaymentsService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TicketCreatedListener {

    private final PaymentsService paymentsService;

    public TicketCreatedListener(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @EventListener
    public void handleTicketCreated(TicketCreatedEvent event) {
        // Demo: flat price = 100
        paymentsService.createPayment(event.ticketId(), event.userId(), 100.0);
    }
}

