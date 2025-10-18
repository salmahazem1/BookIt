package com.example.tickets.mappers;

import com.example.tickets.domain.CreateEventRequest;
import com.example.tickets.domain.CreateTicketTypeRequest;
import com.example.tickets.domain.UpdateEventRequest;
import com.example.tickets.domain.UpdateTicketTypeRequest;
import com.example.tickets.domain.dtos.request.CreateEventRequestDto;
import com.example.tickets.domain.dtos.request.CreateTicketTypeRequestDto;
import com.example.tickets.domain.dtos.request.UpdateEventRequestDto;
import com.example.tickets.domain.dtos.request.UpdateTicketTypeRequestDto;
import com.example.tickets.domain.dtos.response.*;
import com.example.tickets.domain.entities.Event;
import com.example.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);

    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

    UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);

    UpdateEventRequest fromDto(UpdateEventRequestDto dto);

    TicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

    ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

    GetPublishedEventTicketTypesResponseDto toGetPublishedEventTicketTypesResponseDto(TicketType ticketType);

    GetPublishedEventResponseDto toGetPublishedEventResponseDto(Event event);


}