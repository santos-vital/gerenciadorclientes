package com.desafio.gerenciadorclientes_api.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

  private Long id;
  private String username;
  private String password;
  private Boolean isAdmin;
}
