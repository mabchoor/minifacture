package com.enset.mabchour.minifacture.config;

import com.enset.mabchour.minifacture.client.model.Client;
import com.enset.mabchour.minifacture.client.repository.ClientRepository;
import com.enset.mabchour.minifacture.facture.model.Facture;
import com.enset.mabchour.minifacture.facture.model.LigneFacture;
import com.enset.mabchour.minifacture.facture.repository.FactureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitialiser {
    @Bean
    CommandLineRunner initData(ClientRepository clientRepo, FactureRepository factureRepo) {
        return args -> {
            Client c1 = Client.builder()
                    .nom("Mabchour Abderrahmane")
                    .email("abderrahmane.mabchour@example.com")
                    .siret("12345678900011")
                    .dateCreation(LocalDate.of(2024, 6, 1))
                    .build();
            Client c2 = Client.builder()
                    .nom("Mohamed Hassan")
                    .email("mohamed.hassan@example.com")
                    .siret("12345678900012")
                    .dateCreation(LocalDate.of(2024, 6, 2))
                    .build();
            Client c3 = Client.builder()
                    .nom("Fatima Zahra")
                    .email("fatima.zahra@example.com")
                    .siret("12345678900013")
                    .dateCreation(LocalDate.of(2024, 6, 3))
                    .build();

            clientRepo.saveAll(List.of(c1, c2, c3));

            Facture f1 = Facture.builder()
                    .client(c1)
                    .date(LocalDate.of(2024, 6, 10))
                    .build();
            LigneFacture lf1 = LigneFacture.builder()
                    .description("Développement logiciel")
                    .quantite(1)
                    .prixUnitaireHT(1000)
                    .tauxTVA(20)
                    .facture(f1)
                    .build();
            f1.setLignes(List.of(lf1));
            f1.setTotalHT(1000);
            f1.setTotalTVA(200);
            f1.setTotalTTC(1200);

            Facture f2 = Facture.builder()
                    .client(c2)
                    .date(LocalDate.of(2024, 6, 11))
                    .build();
            LigneFacture lf2 = LigneFacture.builder()
                    .description("Consultation")
                    .quantite(2)
                    .prixUnitaireHT(250)
                    .tauxTVA(10)
                    .facture(f2)
                    .build();
            f2.setLignes(List.of(lf2));
            f2.setTotalHT(500);
            f2.setTotalTVA(50);
            f2.setTotalTTC(550);

            factureRepo.saveAll(List.of(f1, f2));
        };
    }
} 