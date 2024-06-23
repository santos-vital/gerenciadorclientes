package com.desafio.gerenciadorclientes_api.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoInput {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotNull
  private Boolean isAdmin;
}
