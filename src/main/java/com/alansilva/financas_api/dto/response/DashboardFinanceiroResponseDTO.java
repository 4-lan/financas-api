package com.alansilva.financas_api.dto.response;

import java.math.BigDecimal;

public record DashboardFinanceiroResponseDTO(
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal saldo,
        Long quantidadeEntradas,
        Long quantidadeSaidas
) {
}
