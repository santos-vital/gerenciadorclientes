package com.desafio.gerenciadorclientes_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.gerenciadorclientes_api.api.model.input.UsuarioDtoInput;
import com.desafio.gerenciadorclientes_api.domain.exception.UsuarioNaoEncontradoException;
import com.desafio.gerenciadorclientes_api.domain.model.Usuario;
import com.desafio.gerenciadorclientes_api.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroUsuarioService {
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  UsuarioDtoInput usuarioDtoInput = new UsuarioDtoInput();

  @SuppressWarnings("null")
  @Transactional
  public Usuario salvar(Usuario usuario) {

    return usuarioRepository.save(usuario);
  }

  @SuppressWarnings("null")
  public Usuario buscarOuFalhar(Long usuarioId) {

    return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
  }
}
