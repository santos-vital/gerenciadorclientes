package com.desafio.gerenciadorclientes_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "O nome deve conter apenas letras, espaços e números")
  private String nome;

  @Column(nullable = false)
  private String cpf;

  @Embedded
  private Endereco endereco;

  @Column(nullable = false)
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Telefone> telefones = new ArrayList<>();

  @Column(nullable = false)
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Email> emails = new ArrayList<>();
}
