package com.alansilva.financas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(max = 50, message = "O nome deve ter no mínimo 50 caracteres")
        String nome

) {
}
