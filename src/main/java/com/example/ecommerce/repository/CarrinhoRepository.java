package com.example.ecommerce.repository;

import com.example.ecommerce.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario_Id(Long usuarioId);
}
