package com.desafio.gerenciadorclientes_api.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO {
  
  private Long id;
  private String numero;
  private String tipo;
}
