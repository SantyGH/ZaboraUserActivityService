package com.zabora.user_activity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabora.user_activity_service.model.dto.CreateHistorialRequest;
import com.zabora.user_activity_service.model.dto.HistorialResponse;
import com.zabora.user_activity_service.model.dto.ResponseRecipes;
import com.zabora.user_activity_service.service.HistorialService;





@RestController
@RequestMapping("/historial")
public class HistorialController {

	
	@GetMapping("/usuario/{userId}/detallado") public ResponseEntity<List<ResponseRecipes>> obtenerHistorialDetallado(@PathVariable Long userId) {

	    List<ResponseRecipes> historial = historialService.obtenerHistorialConRecetas(userId);

	    if (historial.isEmpty()) return ResponseEntity.noContent().build();

	    return ResponseEntity.ok(historial);
}

	
	
    @Autowired
    private HistorialService historialService;

    // -------------------------------
    // Registrar historial de un usuario
    // -------------------------------
    @PostMapping
    public ResponseEntity<HistorialResponse> registrarHistorial(@RequestBody CreateHistorialRequest request) {
        HistorialResponse response = historialService.registrarHistorial(request);
        return ResponseEntity.ok(response);
    }

    // -------------------------------
    // Obtener historial completo de todos los usuarios
    // -------------------------------
    @GetMapping("/todos")
    public ResponseEntity<List<HistorialResponse>> obtenerHistorialCompleto() {
        List<HistorialResponse> historial = historialService.obtenerHistorialCompleto();
        if (historial.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(historial);
    }

    // -------------------------------
    // Obtener historial de un usuario específico
    // -------------------------------
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<HistorialResponse>> obtenerHistorialPorUsuario(@PathVariable Long userId) {
        List<HistorialResponse> historial = historialService.obtenerHistorialPorUsuario(userId);
        if (historial.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(historial);
    }

    // -------------------------------
    // Eliminar todo el historial de un usuario
    // -------------------------------
    @DeleteMapping("/usuario/{userId}")
    public ResponseEntity<String> eliminarHistorialPorUsuario(@PathVariable Long userId) {
        String mensaje = historialService.eliminarHistorialPorUsuario(userId);
        return ResponseEntity.ok(mensaje);
    }

    // -------------------------------
    // Eliminar todo el historial de todos los usuarios
    // -------------------------------
    @DeleteMapping("/todos")
    public ResponseEntity<String> eliminarHistorialCompleto() {
        String mensaje = historialService.eliminarHistorialCompleto();
        return ResponseEntity.ok(mensaje);
    }

    // -------------------------------
    // Eliminar receta específica del historial de un usuario
    // -------------------------------
    @DeleteMapping("/usuario/{userId}/receta/{recipeId}")
    public ResponseEntity<String> eliminarRecetaPorUsuario(@PathVariable Long userId,
                                                           @PathVariable Long recipeId) {
        String mensaje = historialService.eliminarRecetaPorUsuario(userId, recipeId);
        return ResponseEntity.ok(mensaje);
    }

    // -------------------------------
    // Eliminar receta específica del historial de todos los usuarios
    // -------------------------------	
    @DeleteMapping("/todos/receta/{recipeId}")
    public ResponseEntity<String> eliminarRecetaDeTodosUsuarios(@PathVariable Long recipeId) {
        String mensaje = historialService.eliminarRecetaDeTodosUsuarios(recipeId);
        return ResponseEntity.ok(mensaje);
    }
}
