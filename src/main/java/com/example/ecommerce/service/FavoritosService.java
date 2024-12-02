package com.example.ecommerce.service;

import com.example.ecommerce.model.Favoritos;
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.FavoritosRepository;
import com.example.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritosService {

    @Autowired
    private FavoritosRepository favoritosRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public String adicionarAosFavoritos(Long usuarioId, Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        if (produto.isEmpty()) {
            return "Produto não encontrado.";
        }

        Favoritos favoritos = favoritosRepository.findByUsuario_Id(usuarioId).orElseGet(() -> {
            Favoritos novoFavoritos = new Favoritos();
            novoFavoritos.setUsuarioId(usuarioId);
            return favoritosRepository.save(novoFavoritos);
        });

        favoritos.getProdutos().add(produto.get());
        favoritosRepository.save(favoritos);

        return "Produto adicionado aos favoritos!";
    }

    public String removerDosFavoritos(Long usuarioId, Long produtoId) {
        Optional<Favoritos> favoritos = favoritosRepository.findByUsuario_Id(usuarioId);

        if (favoritos.isEmpty()) {
            return "Lista de desejos não encontrada.";
        }

        Favoritos f = favoritos.get();
        f.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));
        favoritosRepository.save(f);

        return "Produto removido dos favoritos!";
    }

    public List<Produto> visualizarFavoritos(Long usuarioId) {
        return favoritosRepository.findByUsuario_Id(usuarioId)
                .map(Favoritos::getProdutos)
                .orElse(List.of());
    }
}
