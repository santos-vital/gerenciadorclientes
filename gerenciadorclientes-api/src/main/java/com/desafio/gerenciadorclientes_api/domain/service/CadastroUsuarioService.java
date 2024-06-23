package com.desafio.gerenciadorclientes_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.gerenciadorclientes_api.domain.exception.UsuarioNaoEncontradoException;
import com.desafio.gerenciadorclientes_api.domain.model.Usuario;
import com.desafio.gerenciadorclientes_api.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroUsuarioService {
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario salvar(Usuario usuario) {

    return usuarioRepository.save(usuario);
  }

  public Usuario buscarOuFalhar(Long usuarioId) {

    return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
  }
}
