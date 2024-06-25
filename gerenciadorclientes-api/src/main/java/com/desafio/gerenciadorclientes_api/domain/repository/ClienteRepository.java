package com.desafio.gerenciadorclientes_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.domain.model.Cliente;

@Component
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  
  boolean existsByCpf(String cpf);
}
