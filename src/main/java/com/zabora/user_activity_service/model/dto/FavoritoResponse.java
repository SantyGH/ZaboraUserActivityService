package com.zabora.user_activity_service.model.dto;

import java.time.LocalDateTime;

public record FavoritoResponse(
        Long id,
        Long userId,
        Long recipeId,
        LocalDateTime fechaGuardado
) {}
