package com.evento.specs;

import com.evento.exceptions.BussinesException;
import com.evento.models.Usuario;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UsuarioSpec {
  private static final String MSG_EMAIL = "Usuário já cadastrado com email: %s.";
  private static final String MSG_CPF = "Usuário já cadastrado com CPF: %s.";

  public void veificarSeExisteUsuarioComEmailDuplicado(Usuario usuario) {
    if (nonNull(usuario)) {
      throw new BussinesException(
              String.format(MSG_EMAIL, usuario.getEmail()));
    }
  }

  public void veificarSeExisteUsuarioComCpfDuplicado(Usuario usuario) {
    if (nonNull(usuario)) {
      throw new BussinesException(
              String.format(MSG_CPF, usuario.getCpf()));
    }
  }


}
