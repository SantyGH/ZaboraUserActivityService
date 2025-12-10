package com.zabora.user_activity_service.model.dto;
import java.util.List;

import com.zabora.user_activity_service.model.dto.recipe.CategoryDTO;
import com.zabora.user_activity_service.model.dto.recipe.FlavorDTO;
import com.zabora.user_activity_service.model.dto.recipe.ImageDTO;
import com.zabora.user_activity_service.model.dto.recipe.IngredientDTO;
import com.zabora.user_activity_service.model.dto.recipe.LicenseDTO;
import com.zabora.user_activity_service.model.dto.recipe.StepDTO;

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
