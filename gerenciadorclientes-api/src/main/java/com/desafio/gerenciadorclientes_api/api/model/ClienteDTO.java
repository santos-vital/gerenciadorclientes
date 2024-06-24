package com.desafio.gerenciadorclientes_api.api.model;

import com.desafio.gerenciadorclientes_api.domain.model.Endereco;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
  
  private Long id;
  private String nome;
  private String cpf;

  @Embedded
  private Endereco endereco;

  private String telefone;
  private String email;
}
