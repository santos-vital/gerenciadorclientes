package com.desafio.gerenciadorclientes_api.domain.exception;

public class EmailInvalidoException extends NegocioException {
  public EmailInvalidoException(String mensagem) {
    super(mensagem);
  }

  public EmailInvalidoException() {
    this(String.format("O e-mail informado é inválido"));
  }
}
