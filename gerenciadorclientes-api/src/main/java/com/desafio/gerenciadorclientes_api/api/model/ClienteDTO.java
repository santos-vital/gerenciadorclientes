package com.desafio.gerenciadorclientes_api.api.model;

import java.util.ArrayList;
import java.util.List;

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

  private List<TelefoneDTO> telefones = new ArrayList<>();
  private List<EmailDTO> emails = new ArrayList<>();
}
