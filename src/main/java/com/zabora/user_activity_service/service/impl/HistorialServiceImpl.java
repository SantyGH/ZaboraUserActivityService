package com.zabora.user_activity_service.service.impl;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.model.entities.HistorialReceta;
import com.zabora.user_activity_service.repository.HistorialRepository;
import com.zabora.user_activity_service.service.HistorialService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    @Override
    public HistorialResponse registrarHistorial(CreateHistorialRequest request) {
        HistorialReceta historial = new HistorialReceta();
        historial.setUserId(request.userId());
        historial.setRecipeId(request.recipeId());

        // Asignamos la fecha actual antes de guardar
        historial.setFechaVista(LocalDateTime.now());

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
}
