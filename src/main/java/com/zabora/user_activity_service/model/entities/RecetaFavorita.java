package com.zabora.user_activity_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recetas_favoritas",
       uniqueConstraints = {
            @UniqueConstraint(name = "uk_favorito_user_recipe", columnNames = {"user_id", "recipe_id"})
       },
       indexes = {
            @Index(name = "idx_favoritos_user", columnList = "user_id")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(name = "fecha_guardado", nullable = false)
    private LocalDateTime fechaGuardado;
}
