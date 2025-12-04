package com.zabora.user_activity_service.controller;

import com.zabora.user_activity_service.model.dto.CreateFavoritoRequest;
import com.zabora.user_activity_service.model.dto.FavoritoResponse;
import com.zabora.user_activity_service.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosService favoritosService;

    // Obtener favoritos por usuario
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoritoResponse>> obtenerFavoritos(@PathVariable Long userId) {
        List<FavoritoResponse> favoritos = favoritosService.obtenerFavoritosPorUsuario(userId);
        if (favoritos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(favoritos);
        }
        return ResponseEntity.ok(favoritos);
    }

    // Agregar a favoritos
    @PostMapping
    public ResponseEntity<?> agregarAFavoritos(@RequestBody CreateFavoritoRequest request) {
        try {
            FavoritoResponse nuevoFavorito = favoritosService.agregarAFavoritos(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoFavorito);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Eliminar favorito
    @DeleteMapping
    public ResponseEntity<String> eliminarFavorito(
            @RequestParam Long userId,
            @RequestParam Long recipeId) {
        try {
            favoritosService.eliminarDeFavoritos(userId, recipeId);
            return ResponseEntity.ok("Receta eliminada de favoritos");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
