package com.desafio.gerenciadorclientes_api.domain.exception;

public class CepIncorretoException extends NegocioException {
  
  public CepIncorretoException(String mensagem) {
    super(mensagem);
  }

  public CepIncorretoException() {
    this("Endereço não encontrado, digite um CEP válido.");
  }
}
