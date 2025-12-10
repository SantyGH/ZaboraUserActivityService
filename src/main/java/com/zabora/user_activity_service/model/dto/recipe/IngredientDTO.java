package com.zabora.user_activity_service.model.dto.recipe;

public record IngredientDTO(
        Long id,
        String name,
        String imageUrl,
        String measurementName
) {}