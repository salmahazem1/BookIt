package com.example.tickets.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketTypeRequest {
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;

}
