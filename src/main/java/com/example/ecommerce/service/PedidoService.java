package com.example.ecommerce.service;

import com.example.ecommerce.model.Pedido;
import com.example.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
