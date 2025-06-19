package com.enset.mabchour.minifacture.facture.repository;

import com.enset.mabchour.minifacture.facture.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClientId(Long clientId);
    List<Facture> findByDate(LocalDate date);
} 