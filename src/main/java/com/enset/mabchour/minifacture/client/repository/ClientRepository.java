package com.enset.mabchour.minifacture.client.repository;

import com.enset.mabchour.minifacture.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
} 