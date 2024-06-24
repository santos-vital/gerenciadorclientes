package com.desafio.gerenciadorclientes_api.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.gerenciadorclientes_api.api.model.input.ClienteDtoInput;
import com.desafio.gerenciadorclientes_api.domain.model.Cliente;

@Component
public class ClienteDtoDisassembler {
  
  @Autowired
  private ModelMapper modelMapper;

  public Cliente toDomainObject(ClienteDtoInput clienteDtoInput) {
    return modelMapper.map(clienteDtoInput, Cliente.class);
  }

  public void copyToDomainObject(ClienteDtoInput usuarioDtoInput, Cliente cliente) {
    modelMapper.map(usuarioDtoInput, cliente);
  }
}
