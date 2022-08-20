package com.etec.tcc.sprint_quiz.controller;

import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.UsuarioLogin;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtem usuario pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Obtem usuario pelo email")
    @GetMapping("/email/{usuario}")
    public ResponseEntity<Usuario> findByNome(@PathVariable String usuario){
        return usuarioRepository.findByUsuario(usuario)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Obtem usuario pelo nome exato")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> findAllByNome(@PathVariable String nome){
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }
    @Operation(summary = "Obtem todos os usuario")
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

//    @PostMapping
//    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
//        if(usuarioRepository.existsById(usuario.getId()))
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//        return ResponseEntity.ok(usuarioRepository.save(usuario));
//    }

//    @PostMapping
//    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
//        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
//    }


    @Operation(summary = "Logar um usuario")
    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> logar(@RequestBody Optional<UsuarioLogin> usuarioLogin){
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Operation(summary = "Cadastrar um usuario")
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody  Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario)
                .map(u -> ResponseEntity.status(HttpStatus.CREATED).body(u))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @Operation(summary = "Atualizar um usuario")
    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.putUsuario(usuario);
    }

    @Operation(summary = "deleta um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        return usuarioService.deleteUsuario(id);
    }







}
