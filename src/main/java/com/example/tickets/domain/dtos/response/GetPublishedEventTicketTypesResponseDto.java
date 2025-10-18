package com.example.tickets.domain.dtos.response;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPublishedEventTicketTypesResponseDto {
    private UUID id;
    private String name;
    private Double price;
    private String description;

}
