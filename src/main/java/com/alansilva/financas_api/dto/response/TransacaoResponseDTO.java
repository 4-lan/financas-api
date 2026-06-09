package com.alansilva.financas_api.dto.response;

import com.alansilva.financas_api.entity.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransacaoResponseDTO(

        UUID idTransacao,
        TipoTransacao tipo,
        BigDecimal valor,
        LocalDate data,
        String descricao,
        String categoria,
        OffsetDateTime dataCriacao
) {
}
