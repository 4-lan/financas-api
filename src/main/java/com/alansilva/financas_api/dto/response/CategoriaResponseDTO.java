package com.alansilva.financas_api.dto.response;

import java.util.UUID;

public record CategoriaResponseDTO(

        UUID idCategoria,
        String nome
) {
}
