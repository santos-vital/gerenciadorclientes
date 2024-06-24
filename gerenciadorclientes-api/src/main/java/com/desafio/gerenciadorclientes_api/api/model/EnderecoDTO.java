package com.desafio.gerenciadorclientes_api.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
  
  private String cep;
  private String logradouro;
  private String complemento;
  private String bairro;
  private String localidade;
  private String uf;
}
