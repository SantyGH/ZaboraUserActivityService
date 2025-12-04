package com.zabora.user_activity_service.service.impl;

import com.zabora.user_activity_service.model.dto.CreateFavoritoRequest;
import java.time.LocalDateTime;

import com.zabora.user_activity_service.model.dto.FavoritoResponse;
import com.zabora.user_activity_service.model.entities.RecetaFavorita;
import com.zabora.user_activity_service.repository.FavoritosRepository;
import com.zabora.user_activity_service.service.FavoritosService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritosServiceImpl implements FavoritosService {

    @Autowired
    private FavoritosRepository favoritosRepository;

    @Override
    public FavoritoResponse agregarAFavoritos(CreateFavoritoRequest request) {

        // Validar si ya existe
        if (favoritosRepository.existsByUserIdAndRecipeId(request.userId(), request.recipeId())) {
            throw new RuntimeException("La receta ya está en favoritos");
        }

        RecetaFavorita nueva = new RecetaFavorita();
        nueva.setUserId(request.userId());
        nueva.setRecipeId(request.recipeId());

      
        nueva.setFechaGuardado(LocalDateTime.now());

        nueva = favoritosRepository.save(nueva);

        return new FavoritoResponse(
                nueva.getId(),
                nueva.getUserId(),
                nueva.getRecipeId(),
                nueva.getFechaGuardado()
        );
    }


    @Override
    public void eliminarDeFavoritos(Long userId, Long recipeId) {
        RecetaFavorita favorito = favoritosRepository
                .findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(() -> new RuntimeException("No se encontró la receta en favoritos"));
        
        favoritosRepository.delete(favorito);
    }


    @Override
    public List<FavoritoResponse> obtenerFavoritosPorUsuario(Long userId) {
        return favoritosRepository.findByUserIdOrderByFechaGuardadoDesc(userId)
                .stream()
                .map(fav -> new FavoritoResponse(
                        fav.getId(),
                        fav.getUserId(),
                        fav.getRecipeId(),
                        fav.getFechaGuardado()
                ))
                .collect(Collectors.toList());
    }
}
