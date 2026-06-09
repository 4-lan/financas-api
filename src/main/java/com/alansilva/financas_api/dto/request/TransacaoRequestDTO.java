package com.alansilva.financas_api.dto.request;

import com.alansilva.financas_api.entity.enums.TipoTransacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoRequestDTO(

        @Schema(
                description = "Tipo da transação",
                example = "ENTRADA"
        )
        TipoTransacao tipo,

        @Schema(
                description = "Valor da transação",
                example = "5000.00"
        )
        BigDecimal valor,

        @Schema(
                description = "Data da transação",
                example = "2026-06-01"
        )
        LocalDate data,

        @Schema(
                description = "Descrição da transação",
                example = "Salário mensal"
        )
        String descricao,

        @Schema(
                description = "ID da categoria",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID idCategoria
) {
}
