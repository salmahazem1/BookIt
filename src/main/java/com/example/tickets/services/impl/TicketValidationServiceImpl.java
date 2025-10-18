package com.example.tickets.services.impl;

import com.example.tickets.domain.entities.TicketValidation;
import com.example.tickets.domain.enums.QrCodeStatusEnum;
import com.example.tickets.domain.enums.TicketValidationMethod;
import com.example.tickets.domain.enums.TicketValidationStatusEnum;
import com.example.tickets.exceptions.TicketNotFoundException;
import com.example.tickets.repositories.QrCodeRepository;
import com.example.tickets.repositories.TicketRepository;
import com.example.tickets.repositories.TicketValidationRepository;
import com.example.tickets.services.TicketValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tickets.domain.entities.QrCode;
import com.example.tickets.domain.entities.Ticket;
import com.example.tickets.exceptions.QrCodeNotFoundException;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {
    private final QrCodeRepository qrCodeRepository;
    private final TicketValidationRepository ticketValidationRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
        QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
                .orElseThrow(() -> new QrCodeNotFoundException(
                        String.format("QR Code with ID %s was not found", qrCodeId)
                ));
        Ticket ticket = qrCode.getTicket();
        return validateTicket(ticket);
    }
    private TicketValidation validateTicket(Ticket ticket) {
        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(TicketValidationMethod.QR_SCAN);
        ticketValidation.setValidatedAt(LocalDateTime.now());
        TicketValidationStatusEnum ticketValidationStatus = ticket.getValidations().stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);
        ticketValidation.setStatus(ticketValidationStatus);
        return ticketValidationRepository.save(ticketValidation);
    }
    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
        return validateTicket(ticket);
    }

}
