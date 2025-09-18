package org.mohyla.itinerary.tickets.application;

import org.mohyla.itinerary.tickets.domain.models.Ticket;
import org.mohyla.itinerary.tickets.domain.persistence.TicketRepository;
import org.mohyla.itinerary.shared.TicketCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {

    private final TicketRepository ticketRepository;
    private final ApplicationEventPublisher events;

    public TicketsService(TicketRepository ticketRepository, ApplicationEventPublisher events) {
        this.ticketRepository = ticketRepository;
        this.events = events;
    }

    public void createTicket(Long userId, Long routeId) {
        Ticket ticket = new Ticket(userId, routeId);
        ticketRepository.save(ticket);

        events.publishEvent(new TicketCreatedEvent(ticket.getId(), userId, routeId));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicket(Long id) {
        return ticketRepository.findById(id);
    }

    public void confirmTicket(Long ticketId) {
        ticketRepository.findById(ticketId).ifPresent(ticket -> {
            ticket.confirm();
            ticketRepository.save(ticket);
        });
    }
}
