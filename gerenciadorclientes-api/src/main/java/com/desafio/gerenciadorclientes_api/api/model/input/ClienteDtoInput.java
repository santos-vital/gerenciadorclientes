package com.desafio.gerenciadorclientes_api.api.model.input;

import com.desafio.gerenciadorclientes_api.domain.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDtoInput {

  @NotBlank
  private String nome;

  @NotBlank
  private String cpf;

  private Endereco endereco;

  @NotBlank
  private String telefone;

  @NotBlank
  private String email;
}
