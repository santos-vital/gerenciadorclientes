package com.desafio.gerenciadorclientes_api.domain.exception;

public class EmailTelefoenNaoInformadoException extends NegocioException {
  
  public EmailTelefoenNaoInformadoException(String mensagem) {
    super(mensagem);
  }

  public EmailTelefoenNaoInformadoException() {
    this(String.format("É necessário incluir pelo menos um telefone ou email"));
  }
}
