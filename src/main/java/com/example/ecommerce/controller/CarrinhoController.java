package com.example.ecommerce.controller;

import com.example.ecommerce.model.Carrinho;
import com.example.ecommerce.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/add/{produtoId}")
    public ResponseEntity<String> addProductToCart(@RequestParam Long usuarioId,
                                                   @PathVariable Long produtoId,
                                                   @RequestParam int quantidade) {
        String resultado = carrinhoService.adicionarAoCarrinho(usuarioId, produtoId, quantidade);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/remove/{produtoId}")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long usuarioId,
                                                        @PathVariable Long produtoId) {
        String resultado = carrinhoService.removerDoCarrinho(usuarioId, produtoId);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<Carrinho> viewCart(@RequestParam Long usuarioId) {
        Carrinho carrinho = carrinhoService.visualizarCarrinho(usuarioId);
        return ResponseEntity.ok(carrinho);
    }
}
