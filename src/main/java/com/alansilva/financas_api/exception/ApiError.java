package com.alansilva.financas_api.exception;

import java.time.OffsetDateTime;

public record ApiError(

        OffsetDateTime timestamp,
        Integer status,
        String error,
        String message
) {
}
