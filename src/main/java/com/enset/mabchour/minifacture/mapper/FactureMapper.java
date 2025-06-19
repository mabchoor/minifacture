package com.enset.mabchour.minifacture.mapper;

import com.enset.mabchour.minifacture.facture.dto.FactureDto;
import com.enset.mabchour.minifacture.facture.dto.LigneFactureDto;
import com.enset.mabchour.minifacture.facture.model.Facture;
import com.enset.mabchour.minifacture.facture.model.LigneFacture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FactureMapper {
    private final ClientMapper clientMapper;

    public FactureDto toDto(Facture facture) {
        if (facture == null) return null;
        return FactureDto.builder()
                .id(facture.getId())
                .client(clientMapper.toDto(facture.getClient()))
                .date(facture.getDate())
                .lignes(toLigneDtoList(facture.getLignes()))
                .totalHT(facture.getTotalHT())
                .totalTVA(facture.getTotalTVA())
                .totalTTC(facture.getTotalTTC())
                .build();
    }

    public Facture toEntity(FactureDto dto) {
        if (dto == null) return null;
        return Facture.builder()
                .id(dto.getId())
                .client(clientMapper.toEntity(dto.getClient()))
                .date(dto.getDate())
                .lignes(toLigneEntityList(dto.getLignes()))
                .totalHT(dto.getTotalHT())
                .totalTVA(dto.getTotalTVA())
                .totalTTC(dto.getTotalTTC())
                .build();
    }

    public List<LigneFactureDto> toLigneDtoList(List<LigneFacture> lignes) {
        if (lignes == null) return null;
        return lignes.stream().map(this::toLigneDto).collect(Collectors.toList());
    }

    public LigneFactureDto toLigneDto(LigneFacture ligne) {
        if (ligne == null) return null;
        return LigneFactureDto.builder()
                .id(ligne.getId())
                .description(ligne.getDescription())
                .quantite(ligne.getQuantite())
                .prixUnitaireHT(ligne.getPrixUnitaireHT())
                .tauxTVA(ligne.getTauxTVA())
                .build();
    }

    public List<LigneFacture> toLigneEntityList(List<LigneFactureDto> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(this::toLigneEntity).collect(Collectors.toList());
    }

    public LigneFacture toLigneEntity(LigneFactureDto dto) {
        if (dto == null) return null;
        return LigneFacture.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .quantite(dto.getQuantite())
                .prixUnitaireHT(dto.getPrixUnitaireHT())
                .tauxTVA(dto.getTauxTVA())
                .build();
    }
} 