package com.enset.mabchour.minifacture.facture.service;

import com.enset.mabchour.minifacture.client.model.Client;
import com.enset.mabchour.minifacture.client.repository.ClientRepository;
import com.enset.mabchour.minifacture.facture.model.Facture;
import com.enset.mabchour.minifacture.facture.model.LigneFacture;
import com.enset.mabchour.minifacture.facture.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service métier pour la gestion des factures (CRUD, calculs, recherche).
 */
@Service
@RequiredArgsConstructor
public class FactureService {
    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;

    /**
     * Retourne la liste de toutes les factures.
     */
    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    /**
     * Recherche une facture par son identifiant.
     */
    public Optional<Facture> findById(Long id) {
        return factureRepository.findById(id);
    }

    /**
     * Recherche les factures d'un client.
     */
    public List<Facture> findByClientId(Long clientId) {
        return factureRepository.findByClientId(clientId);
    }

    /**
     * Recherche les factures par date.
     */
    public List<Facture> findByDate(LocalDate date) {
        return factureRepository.findByDate(date);
    }

    /**
     * Enregistre une facture avec calcul automatique des totaux.
     */
    @Transactional
    public Facture save(Facture facture) {
        // Calcul automatique des totaux
        double totalHT = 0, totalTVA = 0;
        for (LigneFacture ligne : facture.getLignes()) {
            double ligneHT = ligne.getPrixUnitaireHT() * ligne.getQuantite();
            double ligneTVA = ligneHT * (ligne.getTauxTVA() / 100.0);
            totalHT += ligneHT;
            totalTVA += ligneTVA;
            ligne.setFacture(facture);
        }
        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT + totalTVA);
        return factureRepository.save(facture);
    }

    /**
     * Supprime une facture par son identifiant.
     */
    public void deleteById(Long id) {
        factureRepository.deleteById(id);
    }
} 