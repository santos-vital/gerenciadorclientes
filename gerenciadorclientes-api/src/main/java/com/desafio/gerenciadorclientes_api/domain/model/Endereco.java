package com.desafio.gerenciadorclientes_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

  @Column(name = "endereco_cep")
  @NotBlank
  private String cep;

  @Column(name = "endereco_logradouro")
  @NotBlank
  private String logradouro;

  @Column(name = "endereco_complemento")
  private String complemento;

  @Column(name = "endereco_bairro")
  @NotBlank
  private String bairro;

  @Column(name = "endereco_cidade")
  @NotBlank
  private String cidade;

  @Column(name = "endereco_uf")
  @NotBlank
  private String uf;

  private String addCepMask(String cep) {
    if (cep != null && cep.length() == 8) {
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }
    return cep;
  }

  public String getCep() {
    return addCepMask(cep);
  }
}
