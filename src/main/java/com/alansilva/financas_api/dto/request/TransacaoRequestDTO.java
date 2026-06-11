package com.alansilva.financas_api.dto.request;

import com.alansilva.financas_api.entity.enums.TipoTransacao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoRequestDTO(

        @NotNull(message = "O tipo é obrigatório")
        TipoTransacao tipo,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser maior que zero")
        @Digits(
                integer = 10,
                fraction = 2,
                message = "O valor deve possuir no máximo 2 casas decimais"
        )
        BigDecimal valor,

        @NotNull(message = "A data é obrigatória")
        @PastOrPresent(message = "A data não pode ser futura")
        LocalDate data,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(
                max = 255,
                message = "A descrição deve ter no máximo 255 caracteres"
        )
        String descricao,

        @NotNull(message = "A categoria é obrigatória")
        UUID idCategoria
) {
}
