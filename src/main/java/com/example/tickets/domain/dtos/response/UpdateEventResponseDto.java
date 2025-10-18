package com.example.tickets.domain.dtos.response;

import com.example.tickets.domain.enums.EventStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TicketTypeResponseDto> ticketTypes;
}
