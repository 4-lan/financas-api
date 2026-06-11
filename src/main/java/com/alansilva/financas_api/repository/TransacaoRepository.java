package com.alansilva.financas_api.repository;

import com.alansilva.financas_api.entity.Categoria;
import com.alansilva.financas_api.entity.Transacao;
import com.alansilva.financas_api.entity.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    @Query("""
            SELECT COALESCE(SUM(t.valor), 0)
            FROM Transacao t
            WHERE t.tipo = :tipo
            """)
    BigDecimal somarPorTipo(TipoTransacao tipo);

    @Query("""
                        SELECT COUNT(t)
                        FROM Transacao t
                        WHERE t.tipo = tipo
            """)
    Long contarPorTipo(TipoTransacao tipo);

    List<Transacao> findByDataBetween(
            LocalDate dataInicial,
            LocalDate dataFinal
    );

    List<Transacao> findByTipo(TipoTransacao tipo);

    boolean existsByCategoria(Categoria categoria);
}
