package com.desafio.gerenciadorclientes_api.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseDTO {
  
  private String cep;
  private String logradouro;
  private String complemento;
  private String bairro;
  private String cidade;
  private String uf;
}
