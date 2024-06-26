package com.desafio.gerenciadorclientes_api.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.desafio.gerenciadorclientes_api.api.assembler.ClienteDtoAssembler;
import com.desafio.gerenciadorclientes_api.api.assembler.ClienteDtoDisassembler;
import com.desafio.gerenciadorclientes_api.api.model.ClienteDTO;
import com.desafio.gerenciadorclientes_api.api.model.EnderecoDTO;
import com.desafio.gerenciadorclientes_api.api.model.input.ClienteDtoInput;
import com.desafio.gerenciadorclientes_api.core.feignclient.EnderecoFeign;
import com.desafio.gerenciadorclientes_api.domain.exception.CepIncorretoException;
import com.desafio.gerenciadorclientes_api.domain.exception.EmailTelefoenNaoInformadoException;
import com.desafio.gerenciadorclientes_api.domain.exception.NegocioException;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;
import com.desafio.gerenciadorclientes_api.domain.repository.ClienteRepository;
import com.desafio.gerenciadorclientes_api.domain.service.CadastroClienteService;

import feign.FeignException;
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

  @Autowired
  EnderecoFeign enderecoFeign;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ClienteDTO adicionar(@RequestBody @Valid ClienteDtoInput clienteDtoInput) {
    try {
      
      Cliente cliente = clienteDtoDisassembler.toDomainObject(clienteDtoInput);

      EnderecoDTO enderecoDTO = enderecoFeign.buscaEnderecoCep(cliente.getEndereco().getCep());

      cliente.getEndereco().setCep(enderecoDTO.getCep());
      cliente.getEndereco().setLogradouro(enderecoDTO.getLogradouro());

      if(enderecoDTO.getComplemento() == "") {
        cliente.getEndereco().setComplemento(null);
      }

      cliente.getEndereco().setBairro(enderecoDTO.getBairro());
      cliente.getEndereco().setCidade(enderecoDTO.getLocalidade());
      cliente.getEndereco().setUf(enderecoDTO.getUf());

      if (cliente.getEndereco().getCep() == null) {
        throw new CepIncorretoException();
      }
  
      return clienteDtoAssembler.toDTO(cadastroCliente.salvar(cliente));
    } catch (EmailTelefoenNaoInformadoException e) {
      throw new NegocioException(e.getMessage());
    } catch (FeignException e) {
      throw new CepIncorretoException();
    }    
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
  public ClienteDTO atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteDtoInput clienteDtoInput, Cliente cliente) {
    Cliente clienteAtual = cadastroCliente.buscarOuFalhar(clienteId);

    clienteDtoDisassembler.copyToDomainObject(clienteDtoInput, clienteAtual);
    clienteAtual = cadastroCliente.salvar(clienteAtual);

    return clienteDtoAssembler.toDTO(clienteAtual);
  }
}
