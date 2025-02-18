package com.evento.resources;

import com.evento.dtos.UsuarioDTO;
import com.evento.models.Usuario;
import com.evento.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuario = usuarioService.cadastrarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarUsuario(@RequestBody UsuarioDTO usuarioDTO){
       usuarioService.deletarUsuario(usuarioDTO.getId());
       return ResponseEntity.noContent().build();
    }

}
