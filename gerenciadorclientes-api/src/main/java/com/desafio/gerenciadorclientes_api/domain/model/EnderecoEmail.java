package com.desafio.gerenciadorclientes_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EnderecoEmail {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Email(message = "Informe um endereço de e-mail válido")
  private String email;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
}
