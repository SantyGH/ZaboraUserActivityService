package com.zabora.user_activity_service.service;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;

import java.util.List;

public interface HistorialService {

    // Registrar y obtener historial de un usuario
    HistorialResponse registrarHistorial(CreateHistorialRequest request);
    List<HistorialResponse> obtenerHistorialPorUsuario(Long userId);

    // Obtener historial de todos los usuarios
    List<HistorialResponse> obtenerHistorialCompleto();

    // Eliminar historial
    String eliminarHistorialPorUsuario(Long userId);
    String eliminarHistorialCompleto();

    // Eliminar receta específica del historial
    String eliminarRecetaPorUsuario(Long userId, Long recipeId);
    String eliminarRecetaDeTodosUsuarios(Long recipeId);
}
