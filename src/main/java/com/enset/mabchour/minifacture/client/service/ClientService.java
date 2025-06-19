package com.enset.mabchour.minifacture.client.service;

import com.enset.mabchour.minifacture.client.model.Client;
import com.enset.mabchour.minifacture.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service métier pour la gestion des clients (CRUD).
 */
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * Retourne la liste de tous les clients.
     */
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Recherche un client par son identifiant.
     */
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    /**
     * Enregistre ou met à jour un client.
     */
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    /**
     * Supprime un client par son identifiant.
     */
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
} 