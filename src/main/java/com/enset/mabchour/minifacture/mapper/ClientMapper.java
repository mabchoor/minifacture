package com.enset.mabchour.minifacture.mapper;

import com.enset.mabchour.minifacture.client.dto.ClientDto;
import com.enset.mabchour.minifacture.client.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDto toDto(Client client) {
        if (client == null) return null;
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .email(client.getEmail())
                .siret(client.getSiret())
                .dateCreation(client.getDateCreation())
                .build();
    }
    public Client toEntity(ClientDto dto) {
        if (dto == null) return null;
        return Client.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .email(dto.getEmail())
                .siret(dto.getSiret())
                .dateCreation(dto.getDateCreation())
                .build();
    }
} 