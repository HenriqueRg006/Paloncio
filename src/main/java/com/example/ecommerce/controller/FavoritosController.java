package com.example.ecommerce.controller;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class FavoritosController {

    @Autowired
    private FavoritosService favoritosService;

    @PostMapping("/add/{produtoId}")
    public ResponseEntity<String> addToWishlist(@RequestParam Long usuarioId,
                                                @PathVariable Long produtoId) {
        String resultado = favoritosService.adicionarAosFavoritos(usuarioId, produtoId);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/remove/{produtoId}")
    public ResponseEntity<String> removeFromWishlist(@RequestParam Long usuarioId,
                                                     @PathVariable Long produtoId) {
        String resultado = favoritosService.removerDosFavoritos(usuarioId, produtoId);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> viewWishlist(@RequestParam Long usuarioId) {
        List<Produto> favoritos = favoritosService.visualizarFavoritos(usuarioId);
        return ResponseEntity.ok(favoritos);
    }
}
