package com.example.tickets.domain.dtos.request;

import com.example.tickets.domain.enums.TicketValidationMethod;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationRequestDto {
    private UUID id;
    private TicketValidationMethod method;
}
