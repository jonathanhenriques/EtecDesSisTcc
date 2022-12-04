package com.etec.tcc.sprint_quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etec.tcc.sprint_quiz.exception.SenhaInvalidaException;
import com.etec.tcc.sprint_quiz.model.Usuario;
import com.etec.tcc.sprint_quiz.model.dto.TokenDTO;
import com.etec.tcc.sprint_quiz.model.dto.UsuarioLogin2;
import com.etec.tcc.sprint_quiz.repository.UsuarioRepository;
import com.etec.tcc.sprint_quiz.security.JwtService;
import com.etec.tcc.sprint_quiz.security.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController()
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository; //remover para acessar apenas pelo usuarioService se possivel

    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Obtem usuario pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
                
    } 

    @Operation(summary = "Obtem usuario pelo email")
    @GetMapping("/email/{usuario}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String usuario) {
        return usuarioRepository.findByEmail(usuario)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtem usuario pelo nome exato")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> findAllByNome(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @Operation(summary = "Obtem todos os usuario")
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }


    @Operation(summary = "Logar um usuario")
    @PostMapping("/logar")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody UsuarioLogin2 usuarioLogin) {
        try {
        	Usuario usuario = Usuario.builder()
        			.email(usuarioLogin.getEmail())
        			.senha(usuarioLogin.getSenha()).build();
        	
        	UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
        	String token = jwtService.gerarToken(usuario);
        	return  ResponseEntity.ok(new TokenDTO(usuario.getEmail(), token));
        	
        }catch (UsernameNotFoundException | SenhaInvalidaException e) {
        	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
		
    }

    @Operation(summary = "Cadastrar um usuario")
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(u -> ResponseEntity.status(HttpStatus.CREATED).body(u))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

//    @Operation(summary = "Atualizar um usuario")
//    @PutMapping("/atualizar")
//    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
//        return usuarioService.atualizarUsuario(usuario)
//                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }

    @Operation(summary = "deleta um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(u -> {
                    usuarioRepository.delete(u);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
