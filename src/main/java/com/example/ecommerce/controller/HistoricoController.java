package com.example.ecommerce.controller;

import com.example.ecommerce.model.Pedido;
import com.example.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class HistoricoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/history")
    public ResponseEntity<List<Pedido>> viewOrderHistory(@RequestParam Long usuarioId) {
        List<Pedido> pedidos = pedidoService.listarPedidos(usuarioId);
        return ResponseEntity.ok(pedidos);
    }
}
