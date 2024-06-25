package com.desafio.gerenciadorclientes_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.gerenciadorclientes_api.domain.exception.ClienteNaoEncontradoException;
import com.desafio.gerenciadorclientes_api.domain.exception.EmailTelefoenNaoInformadoException;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;
import com.desafio.gerenciadorclientes_api.domain.model.Email;
import com.desafio.gerenciadorclientes_api.domain.model.Telefone;
import com.desafio.gerenciadorclientes_api.domain.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  @SuppressWarnings("null")
  @Transactional
  public Cliente salvar(Cliente cliente) {

    for (Email email : cliente.getEmails()) {
      email.setCliente(cliente);
    }

    for (Telefone telefone : cliente.getTelefones()) {
      telefone.setCliente(cliente);
    }

    if(cliente.getEmails().size() < 1 || cliente.getTelefones().size() < 1) {
      throw new EmailTelefoenNaoInformadoException(String.format("É necessário incluir pelo menos um telefone ou email", 400));
    }

    return clienteRepository.save(cliente);
  }

  @SuppressWarnings("null")
  public Cliente buscarOuFalhar(Long clienteId) {

    return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
  }
}
