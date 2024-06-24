package com.desafio.gerenciadorclientes_api.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDtoInput {
  
  @NotBlank
  private String cep;

  @NotBlank
  private String logradouro;

  @NotBlank
  private String numero;

  private String complemento;

  @NotBlank
  private String bairro;

  @NotBlank
  private String cidade;

  @NotBlank
  private String uf;
}
