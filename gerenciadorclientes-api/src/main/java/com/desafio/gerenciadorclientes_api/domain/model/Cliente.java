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
