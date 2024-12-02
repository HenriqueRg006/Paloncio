package com.example.ecommerce.controller;

import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user.isPresent()) {
            if (passwordEncoder.matches(usuario.getSenha(), user.get().getSenha())) {
                return ResponseEntity.ok("Login bem-sucedido! Bem-vindo, " + user.get().getNomeUsuario());
            } else {
                return ResponseEntity.status(401).body("Senha incorreta!");
            }
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado!");
        }
    }
}
