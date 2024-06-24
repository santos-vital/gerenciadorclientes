package com.desafio.gerenciadorclientes_api.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.api.model.ClienteDTO;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;

@Component
public class ClienteDtoAssembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public ClienteDTO toDTO(Cliente cliente) {

    return modelMapper.map(cliente, ClienteDTO.class);
  }

  public List<ClienteDTO> toCollectionDtos(List<Cliente> clientes) {

    return clientes.stream().map(cliente -> toDTO(cliente)).collect(Collectors.toList());
  }
}
