package com.zabora.user_activity_service.service.impl;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.model.entities.HistorialReceta;
import com.zabora.user_activity_service.repository.HistorialRepository;
import com.zabora.user_activity_service.service.HistorialService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

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

 // -------------------------------
 // ELIMINACIONES
 // -------------------------------
 @Override
 @Transactional
 public String eliminarHistorialPorUsuario(Long userId) {
     historialRepository.deleteByUserId(userId); // tu método existente
     return "Historial del usuario " + userId + " eliminado correctamente";
 }

 @Override
 @Transactional
 public String eliminarHistorialCompleto() {
     historialRepository.deleteAll(); // tu método existente
     return "Todos los historiales eliminados correctamente";
 }

 @Override
 @Transactional
 public String eliminarRecetaPorUsuario(Long userId, Long recipeId) {
     historialRepository.deleteByUserIdAndRecipeId(userId, recipeId); // tu método existente
     return "Receta " + recipeId + " eliminada del historial del usuario " + userId;
 }

 @Override
 @Transactional
 public String eliminarRecetaDeTodosUsuarios(Long recipeId) {
     historialRepository.deleteByRecipeId(recipeId); // tu método existente
     return "Receta " + recipeId + " eliminada de todos los historiales";
 }

}
