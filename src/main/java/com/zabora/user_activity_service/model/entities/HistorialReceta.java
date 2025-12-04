package com.zabora.user_activity_service.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_recetas",
       indexes = {
            @Index(name = "idx_historial_user", columnList = "user_id"),
            @Index(name = "idx_historial_recipe", columnList = "recipe_id")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(name = "fecha_vista", nullable = false, updatable = false)
    private LocalDateTime fechaVista;

    /**
     * Se ejecuta justo antes de persistir la entidad
     * para asignar automáticamente la fecha actual.
     */
    @PrePersist
    public void prePersist() {
        if (this.fechaVista == null) {
            this.fechaVista = LocalDateTime.now();
        }
    }
}
