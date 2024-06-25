package com.desafio.gerenciadorclientes_api.api.model.input;

import java.util.List;

import com.desafio.gerenciadorclientes_api.api.model.EmailDTO;
import com.desafio.gerenciadorclientes_api.api.model.TelefoneDTO;
import com.desafio.gerenciadorclientes_api.domain.model.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDtoInput {

  @NotBlank
  private String nome;

  @NotBlank
  private String cpf;

  @NotNull
  @Valid
  private Endereco endereco;

  @NotNull
  private List<TelefoneDTO> telefones;

  @NotNull
  private List<EmailDTO> emails;
}
