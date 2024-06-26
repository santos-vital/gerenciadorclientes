package com.desafio.gerenciadorclientes_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.domain.model.EnderecoEmail;

@Component
public interface EmailRepository extends JpaRepository<EnderecoEmail, Long> {
  
}
