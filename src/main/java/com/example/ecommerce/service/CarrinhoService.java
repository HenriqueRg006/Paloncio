package com.example.ecommerce.service;

import com.example.ecommerce.model.Carrinho;
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.CarrinhoRepository;
import com.example.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public String adicionarAoCarrinho(Long usuarioId, Long produtoId, int quantidade) {
        Optional<Carrinho> carrinho = carrinhoRepository.findByUsuario_Id(usuarioId);
        Optional<Produto> produto = produtoRepository.findById(produtoId);

        if (produto.isEmpty()) {
            return "Produto não encontrado.";
        }

        Carrinho c = carrinho.orElseGet(() -> {
            Carrinho novoCarrinho = new Carrinho();
            novoCarrinho.setUsuarioId(usuarioId);
            return carrinhoRepository.save(novoCarrinho);
        });

        c.getProdutos().add(produto.get());
        carrinhoRepository.save(c);

        return "Produto adicionado ao carrinho!";
    }

    public String removerDoCarrinho(Long usuarioId, Long produtoId) {
        Optional<Carrinho> carrinho = carrinhoRepository.findByUsuario_Id(usuarioId);

        if (carrinho.isEmpty()) {
            return "Carrinho não encontrado.";
        }

        Carrinho c = carrinho.get();
        c.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));
        carrinhoRepository.save(c);

        return "Produto removido do carrinho!";
    }

    public Carrinho visualizarCarrinho(Long usuarioId) {
        return carrinhoRepository.findByUsuario_Id(usuarioId).orElse(null);
    }
}
