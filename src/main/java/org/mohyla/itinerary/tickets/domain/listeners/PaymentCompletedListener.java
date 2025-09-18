package org.mohyla.itinerary.tickets.domain.listeners;

import org.mohyla.itinerary.shared.PaymentCompletedEvent;
import org.mohyla.itinerary.tickets.application.TicketsService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentCompletedListener {
    private final TicketsService ticketsService;

    public PaymentCompletedListener(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @EventListener
    public void handlePaymentCompleted(PaymentCompletedEvent e){
        ticketsService.confirmTicket(e.ticketId());
    }
}
