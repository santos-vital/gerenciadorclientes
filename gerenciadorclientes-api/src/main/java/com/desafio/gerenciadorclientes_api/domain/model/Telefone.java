package com.desafio.gerenciadorclientes_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Telefone {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String numero;

  @Column(nullable = false)
  private String tipo;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
}
