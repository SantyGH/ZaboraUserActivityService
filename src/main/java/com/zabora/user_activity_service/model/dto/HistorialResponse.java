package com.zabora.user_activity_service.model.dto;

import java.time.LocalDateTime;

public record HistorialResponse(
        Long id,
        Long userId,
        Long recipeId,
        LocalDateTime fechaVista
) {}
