package com.example.tickets.domain.dtos.response;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeResponseDto {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private Integer totalAvailable;
}
