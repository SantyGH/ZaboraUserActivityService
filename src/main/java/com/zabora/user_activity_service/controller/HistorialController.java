package com.zabora.user_activity_service.controller;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @PostMapping
    public HistorialResponse registrarHistorial(@RequestBody CreateHistorialRequest request) {
        return historialService.registrarHistorial(request);
    }

    @GetMapping("/{userId}")
    public List<HistorialResponse> obtenerHistorial(@PathVariable Long userId) {
        return historialService.obtenerHistorialPorUsuario(userId);
    }
}
