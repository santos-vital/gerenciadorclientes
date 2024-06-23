package com.desafio.gerenciadorclientes_api.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
  
  public UsuarioNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public UsuarioNaoEncontradoException(Long usuarioId) {
    this(String.format("Não existe um usuário com o código %d", usuarioId));
  }
}
