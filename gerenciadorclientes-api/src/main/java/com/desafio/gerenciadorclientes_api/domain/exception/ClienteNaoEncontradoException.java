package com.desafio.gerenciadorclientes_api.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {
  
  public ClienteNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ClienteNaoEncontradoException(Long clienteId) {
    this(String.format("Não existe um cadastro de cliente com o código %d", clienteId));
  }
}
