package com.enset.mabchour.minifacture.client.controller;

import com.enset.mabchour.minifacture.client.dto.ClientDto;
import com.enset.mabchour.minifacture.client.model.Client;
import com.enset.mabchour.minifacture.client.service.ClientService;
import com.enset.mabchour.minifacture.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour la gestion des clients.
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    /**
     * Liste tous les clients.
     */
    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Détail d'un client par id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return clientService.findById(id)
                .map(clientMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau client.
     */
    @PostMapping
    public ClientDto create(@RequestBody ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        client.setDateCreation(java.time.LocalDate.now());
        return clientMapper.toDto(clientService.save(client));
    }

    /**
     * Supprime un client par id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteById(id);
    }
} 