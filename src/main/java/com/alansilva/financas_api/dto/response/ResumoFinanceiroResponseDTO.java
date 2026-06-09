package com.alansilva.financas_api.dto.response;

import java.math.BigDecimal;

public record ResumoFinanceiroResponseDTO(
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal saldo
) {
}
