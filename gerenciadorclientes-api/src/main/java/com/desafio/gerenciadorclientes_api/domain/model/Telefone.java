package com.desafio.gerenciadorclientes_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
  @Size(min = 10, max = 11, message = "O número de telefone deve ter 10 ou 11 dígitos")
  @Pattern(regexp = "\\d{10,11}", message = "O número de telefone deve conter apenas dígitos")
  private String numero;

  @Column(nullable = false)
  private String tipo;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getNumero() {
      return formatTelefone(numero);
  }

  private String formatTelefone(String telefone) {
      if (telefone != null) {
          // Exemplo de formatação: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX
          if (telefone.length() == 10) {
              return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
          } else if (telefone.length() == 11) {
              return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
          }
      }
      return telefone;
  }
}
