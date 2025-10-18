package com.example.tickets.domain.dtos.response;

import com.example.tickets.domain.enums.TicketValidationStatusEnum;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationResponseDto {
    private UUID ticketId;
    private TicketValidationStatusEnum status;
}
