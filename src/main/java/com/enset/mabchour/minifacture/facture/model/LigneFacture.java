package com.enset.mabchour.minifacture.facture.model;

import com.enset.mabchour.minifacture.util.validator.TauxTva;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @Min(1)
    private int quantite;

    @Min(0)
    private double prixUnitaireHT;

    @NotNull
    @TauxTva
    private double tauxTVA;

    @ManyToOne(optional = false)
    private Facture facture;
} 