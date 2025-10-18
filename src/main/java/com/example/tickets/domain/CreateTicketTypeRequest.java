package com.example.tickets.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeRequest {
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
}
