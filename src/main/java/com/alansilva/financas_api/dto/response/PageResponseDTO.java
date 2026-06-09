package com.alansilva.financas_api.dto.response;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> dados,
        int pagina,
        int tamanho,
        long totalElementos,
        int totalPaginas
) {
}
