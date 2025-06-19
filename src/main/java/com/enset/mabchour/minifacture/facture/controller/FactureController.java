package com.enset.mabchour.minifacture.facture.controller;

import com.enset.mabchour.minifacture.facture.dto.FactureDto;
import com.enset.mabchour.minifacture.facture.model.Facture;
import com.enset.mabchour.minifacture.facture.service.FactureService;
import com.enset.mabchour.minifacture.mapper.FactureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour la gestion des factures.
 */
@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
public class FactureController {
    private final FactureService factureService;
    private final FactureMapper factureMapper;

    /**
     * Liste toutes les factures, avec filtres optionnels.
     */
    @GetMapping
    public List<FactureDto> getAll(@RequestParam(required = false) Long clientId,
                                   @RequestParam(required = false) String date) {
        if (clientId != null) {
            return factureService.findByClientId(clientId).stream().map(factureMapper::toDto).collect(Collectors.toList());
        }
        if (date != null) {
            return factureService.findByDate(LocalDate.parse(date)).stream().map(factureMapper::toDto).collect(Collectors.toList());
        }
        return factureService.findAll().stream().map(factureMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Détail d'une facture par id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getById(@PathVariable Long id) {
        return factureService.findById(id)
                .map(factureMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouvelle facture.
     */
    @PostMapping
    public FactureDto create(@RequestBody FactureDto dto) {
        Facture facture = factureMapper.toEntity(dto);
        facture.setDate(java.time.LocalDate.now());
        return factureMapper.toDto(factureService.save(facture));
    }

    /**
     * Supprime une facture par id.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        factureService.deleteById(id);
    }
} 