package com.enset.mabchour.minifacture.facture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneFactureDto {
    private Long id;
    private String description;
    private int quantite;
    private double prixUnitaireHT;
    private double tauxTVA;
} 