package com.example.tickets.domain.dtos.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPublishedEventResponseDto {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private List<GetPublishedEventTicketTypesResponseDto> ticketTypes = new ArrayList<>();
}
