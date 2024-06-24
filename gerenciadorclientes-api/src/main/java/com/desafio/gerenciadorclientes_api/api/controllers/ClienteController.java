package com.desafio.gerenciadorclientes_api.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.desafio.gerenciadorclientes_api.api.assembler.ClienteDtoAssembler;
import com.desafio.gerenciadorclientes_api.api.assembler.ClienteDtoDisassembler;
import com.desafio.gerenciadorclientes_api.api.model.ClienteDTO;
import com.desafio.gerenciadorclientes_api.api.model.input.ClienteDtoInput;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;
import com.desafio.gerenciadorclientes_api.domain.repository.ClienteRepository;
import com.desafio.gerenciadorclientes_api.domain.service.CadastroClienteService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
  
  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private CadastroClienteService cadastroCliente;

  @Autowired
  private ClienteDtoAssembler clienteDtoAssembler;

  @Autowired
  private ClienteDtoDisassembler clienteDtoDisassembler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClienteDTO adicionar(@RequestBody @Valid ClienteDtoInput clienteDtoInput) {
    Cliente cliente = clienteDtoDisassembler.toDomainObject(clienteDtoInput);

    return clienteDtoAssembler.toDTO(cadastroCliente.salvar(cliente));
  }

  @GetMapping
  public List<ClienteDTO> listar() {

    return clienteDtoAssembler.toCollectionDTO(clienteRepository.findAll());
  }

  @GetMapping("/{clienteId}")
  public ClienteDTO buscar(@PathVariable Long clienteId) {
    Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);

    return clienteDtoAssembler.toDTO(cliente);
  }

  @PutMapping("/{clienteId}") 
  public ClienteDTO atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteDtoInput clienteDtoInput) {
    Cliente clienteAtual = cadastroCliente.buscarOuFalhar(clienteId);

    clienteDtoDisassembler.copyToDomainObject(clienteDtoInput, clienteAtual);

    return clienteDtoAssembler.toDTO(cadastroCliente.salvar(clienteAtual));
  }
}
