package com.zabora.user_activity_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.model.dto.ResponseRecipes;
import com.zabora.user_activity_service.model.entities.HistorialReceta;
import com.zabora.user_activity_service.repository.HistorialRepository;
import com.zabora.user_activity_service.repository.RecipeClient;
import com.zabora.user_activity_service.service.HistorialService;
import feign.FeignException;
@Service
public class HistorialServiceImpl implements HistorialService {

    private final HistorialRepository historialRepository;
    private final RecipeClient recipeClient;

    public HistorialServiceImpl(HistorialRepository historialRepository,
                                RecipeClient recipeClient) {
        this.historialRepository = historialRepository;
        this.recipeClient = recipeClient;
    }

    @Override
    public HistorialResponse registrarHistorial(CreateHistorialRequest request) {
        HistorialReceta historial = HistorialReceta.builder()
                .userId(request.userId())
                .recipeId(request.recipeId())
                .build();

        historial = historialRepository.save(historial);

        return new HistorialResponse(
                historial.getId(),
                historial.getUserId(),
                historial.getRecipeId(),
                historial.getFechaVista()
        );
    }

    @Override
    public List<HistorialResponse> obtenerHistorialPorUsuario(Long userId) {
        return historialRepository.findByUserIdOrderByFechaVistaDesc(userId)
                .stream()
                .map(h -> new HistorialResponse(
                        h.getId(),
                        h.getUserId(),
                        h.getRecipeId(),
                        h.getFechaVista()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialResponse> obtenerHistorialCompleto() {
        return historialRepository.findAll()
                .stream()
                .map(h -> new HistorialResponse(
                        h.getId(),
                        h.getUserId(),
                        h.getRecipeId(),
                        h.getFechaVista()
                ))
                .sorted((a, b) -> b.fechaVista().compareTo(a.fechaVista()))
                .collect(Collectors.toList());
    }


    
    @Override
public List<ResponseRecipes> obtenerHistorialConRecetas(Long userId) {

    List<HistorialReceta> historial = historialRepository.findByUserIdOrderByFechaVistaDesc(userId);

    List<Long> recipeIds = historial.stream()
            .map(HistorialReceta::getRecipeId)
            .distinct()
            .toList();

    if (recipeIds.isEmpty()) return List.of();

    try {
        return recipeClient.getRecipesByIds(recipeIds);

    } catch (FeignException.NotFound e) {
        // Error 404 desde recipe-service
        throw new RuntimeException("Alguna de las recetas no se encuentra disponible");
    
    } catch (FeignException e) {
        // Error 500, timeouts, servicio caído, etc.
        throw new RuntimeException("No se pudo obtener la información de las recetas. Intenta más tarde.");
    }
}




    // ============================================================
    // ELIMINACIONES
    // ============================================================
    @Override
    @Transactional
    public String eliminarHistorialPorUsuario(Long userId) {
        historialRepository.deleteByUserId(userId);
        return "Historial del usuario " + userId + " eliminado correctamente";
    }

    @Override
    @Transactional
    public String eliminarHistorialCompleto() {
        historialRepository.deleteAll();
        return "Todos los historiales eliminados correctamente";
    }

    @Override
    @Transactional
    public String eliminarRecetaPorUsuario(Long userId, Long recipeId) {
        historialRepository.deleteByUserIdAndRecipeId(userId, recipeId);
        return "Receta " + recipeId + " eliminada del historial del usuario " + userId;
    }

    @Override
    @Transactional
    public String eliminarRecetaDeTodosUsuarios(Long recipeId) {
        historialRepository.deleteByRecipeId(recipeId);
        return "Receta " + recipeId + " eliminada de todos los historiales";
    }
}