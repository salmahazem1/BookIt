package com.example.tickets.services.impl;

import com.example.tickets.domain.entities.Ticket;
import com.example.tickets.repositories.TicketRepository;
import com.example.tickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Page<Ticket> listTicketsForUser(UUID userId, Pageable pageable) {
        return ticketRepository.findByPurchaserId(userId, pageable);
    }

    @Override
    public Optional<Ticket> getTicketForUser(UUID userId, UUID ticketId) {
        return ticketRepository.findByIdAndPurchaserId(ticketId, userId);
    }

}
