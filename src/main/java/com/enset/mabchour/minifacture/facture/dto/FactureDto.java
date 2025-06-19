package com.enset.mabchour.minifacture.facture.dto;

import com.enset.mabchour.minifacture.client.dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureDto {
    private Long id;
    private ClientDto client;
    private LocalDate date;
    private List<LigneFactureDto> lignes;
    private double totalHT;
    private double totalTVA;
    private double totalTTC;
} 