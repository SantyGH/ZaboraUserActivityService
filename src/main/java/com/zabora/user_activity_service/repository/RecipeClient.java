package com.zabora.user_activity_service.repository;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zabora.user_activity_service.model.dto.ResponseRecipes;

@FeignClient(name = "recipe-service")
public interface RecipeClient {

    @GetMapping("/api/recipes/multiple")
    List<ResponseRecipes> getRecipesByIds(@RequestParam("ids") List<Long>ids);
}