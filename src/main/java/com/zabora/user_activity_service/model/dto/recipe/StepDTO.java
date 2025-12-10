package com.zabora.user_activity_service.model.dto.recipe;

public record StepDTO(
        Long id,
        Integer stepOrder,
        String description,
        Integer timeSeconds,
        String customImageUrl
) {}
