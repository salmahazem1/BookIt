package com.example.tickets.domain.dtos.response;
import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketTicketTypeResponseDto {
    private UUID id;
    private String name;
    private Double price;

}
