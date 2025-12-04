package com.zabora.user_activity_service.repository;

import java.util.Optional;

import com.zabora.user_activity_service.model.entities.RecetaFavorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritosRepository extends JpaRepository<RecetaFavorita, Long> {

    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);

    long countByUserId(Long userId);

    List<RecetaFavorita> findByUserIdOrderByFechaGuardadoDesc(Long userId);

    Optional<RecetaFavorita> findByUserIdAndRecipeId(Long userId, Long recipeId);
    void deleteByUserIdAndRecipeId(Long userId, Long recipeId);
}
	