package com.desafio.gerenciadorclientes_api.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
  
  private Long id;

  @NotBlank
  private String email;
}
