package com.zabora.user_activity_service.model.dto;

import jakarta.validation.constraints.NotNull;

public record CreateFavoritoRequest(

        @NotNull(message = "El userId es obligatorio")
        Long userId,

        @NotNull(message = "El recipeId es obligatorio")
        Long recipeId

) {}
