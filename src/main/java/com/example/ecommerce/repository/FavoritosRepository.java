package com.example.ecommerce.repository;

import com.example.ecommerce.model.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {
    Optional<Favoritos> findByUsuario_Id(Long usuarioId);
}
