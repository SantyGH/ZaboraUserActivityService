package com.zabora.user_activity_service.service;

import java.util.List;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.model.dto.ResponseRecipes;

public interface HistorialService {

    // Registrar y obtener historial de un usuario
    HistorialResponse registrarHistorial(Long userId, CreateHistorialRequest request);
    List<HistorialResponse> obtenerHistorialPorUsuario(Long userId);

    // Obtener historial de todos los usuarios
    List<HistorialResponse> obtenerHistorialCompleto();
    List<ResponseRecipes> obtenerHistorialConRecetas(Long userId);
    // Eliminar historial
    String eliminarHistorialPorUsuario(Long userId);
    String eliminarHistorialCompleto();

    // Eliminar receta específica del historial
    String eliminarRecetaPorUsuario(Long userId, Long recipeId);
    String eliminarRecetaDeTodosUsuarios(Long recipeId);
}