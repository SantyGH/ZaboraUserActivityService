package com.zabora.user_activity_service.model.dto.recipe;

import java.util.List;

public record ResponseRecipes(
        Long id,
        String title,
        String shortDescription,
        Integer servings,
        Integer totalTimeMin,
        String difficulty,
        LicenseDTO license,
        List<CategoryDTO> categories,
        List<FlavorDTO> flavors,
        List<ImageDTO> images,
        List<IngredientDTO> ingredients,
        List<StepDTO> steps
) {}
