package com.zabora.user_activity_service.service;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;

import java.util.List;

public interface HistorialService {

    HistorialResponse registrarHistorial(CreateHistorialRequest request);

    List<HistorialResponse> obtenerHistorialPorUsuario(Long userId);
}
