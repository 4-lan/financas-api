package com.alansilva.financas_api.repository;

import com.alansilva.financas_api.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    boolean existsByNomeIgnoreCase(String nome);
}
