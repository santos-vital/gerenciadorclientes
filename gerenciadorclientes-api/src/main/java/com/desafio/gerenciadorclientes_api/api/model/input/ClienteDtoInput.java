package com.desafio.gerenciadorclientes_api.api.model.input;

import java.util.List;

import com.desafio.gerenciadorclientes_api.api.model.EmailDTO;
import com.desafio.gerenciadorclientes_api.api.model.EnderecoDTO;
import com.desafio.gerenciadorclientes_api.api.model.TelefoneDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  private EnderecoDTO endereco;

  @NotNull
  private List<TelefoneDTO> telefones;

  @NotNull
  private List<EmailDTO> emails;
}
