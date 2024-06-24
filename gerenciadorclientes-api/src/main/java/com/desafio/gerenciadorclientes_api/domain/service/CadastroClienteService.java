package com.desafio.gerenciadorclientes_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.gerenciadorclientes_api.domain.exception.ClienteNaoEncontradoException;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;
import com.desafio.gerenciadorclientes_api.domain.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  @Transactional
  public Cliente salvar(Cliente cliente) {

    return clienteRepository.save(cliente);
  }

  public Cliente buscarOuFalhar(Long clienteId) {

    return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
  }
}
