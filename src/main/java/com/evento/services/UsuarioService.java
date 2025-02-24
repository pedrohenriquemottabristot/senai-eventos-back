package com.evento.services;

import com.evento.dtos.UsuarioDTO;
import com.evento.exceptions.BussinesException;
import com.evento.models.Usuario;
import com.evento.repositories.UsuarioRepository;
import com.evento.specs.UsuarioSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioSpec usuarioSpec;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {


        Usuario usuarioEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        usuarioSpec.veificarSeExisteUsuarioComEmailDuplicado(usuarioEmail);

        Usuario usuarioCpf = usuarioRepository.findByCpf(usuarioDTO.getCpf()).orElse(null);
        usuarioSpec.veificarSeExisteUsuarioComCpfDuplicado(usuarioCpf);

        Usuario usuario = converterUsuarioDTOParaUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDTO(usuario);
    }

    public UsuarioDTO converterUsuarioParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento());
        usuarioDTO.setPerfil(usuario.getPerfil());
        usuarioDTO.setVerificado(usuario.isVerificado());
        return usuarioDTO;
    }

    public Usuario converterUsuarioDTOParaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setDataNascimento(usuarioDTO.getDataNascimento());
        usuario.setPerfil(usuarioDTO.getPerfil());
        usuario.setVerificado(usuarioDTO.isVerificado());
        return usuario;
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BussinesException("Usuário não encontrado"));
        return converterUsuarioParaUsuarioDTO(usuario);
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        if (Objects.isNull(usuarioDTO.getId()))
            throw new BussinesException("Id não pode ser nulo");

        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new BussinesException("Usuário não encontrado"));

        usuario = converterUsuarioDTOParaUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return converterUsuarioParaUsuarioDTO(usuario);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return converterUsuarioParaUsuarioDTO(usuario);
    }
}
