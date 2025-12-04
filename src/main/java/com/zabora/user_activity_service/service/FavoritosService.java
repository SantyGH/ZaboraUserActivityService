package com.zabora.user_activity_service.service;

import com.zabora.user_activity_service.model.dto.CreateFavoritoRequest;
import com.zabora.user_activity_service.model.dto.FavoritoResponse;

import java.util.List;

public interface FavoritosService {

    FavoritoResponse agregarAFavoritos(CreateFavoritoRequest request);

    void eliminarDeFavoritos(Long userId, Long recipeId);

    List<FavoritoResponse> obtenerFavoritosPorUsuario(Long userId);
}
