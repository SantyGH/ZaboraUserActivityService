package com.zabora.user_activity_service.repository;

import com.zabora.user_activity_service.model.entities.HistorialReceta; // Tu entidad
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

	
public interface HistorialRepository extends JpaRepository<HistorialReceta, Long> {

    // Obtener historial de un usuario
    List<HistorialReceta> findByUserIdOrderByFechaVistaDesc(Long userId);

    // Borrar historial completo de un usuario
    @Modifying
    @Transactional
    void deleteByUserId(Long userId);

    // Borrar historial de todos los usuarios para una receta específica
    @Modifying
    @Transactional
    void deleteByRecipeId(Long recipeId);

    // Borrar una receta específica del historial de un usuario
    @Modifying
    @Transactional
    void deleteByUserIdAndRecipeId(Long userId, Long recipeId);

    // Nuevos métodos para encontrar registros antes de eliminar
    List<HistorialReceta> findByUserIdAndRecipeId(Long userId, Long recipeId);
    List<HistorialReceta> findByRecipeId(Long recipeId);
}
