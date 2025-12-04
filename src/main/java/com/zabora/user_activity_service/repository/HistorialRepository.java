package com.zabora.user_activity_service.repository;

import com.zabora.user_activity_service.model.entities.HistorialReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialRepository extends JpaRepository<HistorialReceta, Long> {

    List<HistorialReceta> findByUserIdOrderByFechaVistaDesc(Long userId);
}
